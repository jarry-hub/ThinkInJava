package com.phicomm.big.data.module.authorization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import com.phicomm.big.data.config.CommonPropertiesConfig;
import com.phicomm.big.data.dao.mysql.TokenValidateMapper;
import com.phicomm.big.data.model.authorization.TokenBean;
import com.phicomm.big.data.module.ntp.NTPServer;
import com.phicomm.big.data.module.ntp.NTPServerImp;
import com.phicomm.big.data.utils.ExceptionUtil;
import com.phicomm.big.data.utils.SecurityUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.JedisCluster;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

/**
 * 白名单处理
 * Created by yufei.liu
 */
class WhiteListConfig {

    /**
     * 日志
     */
    private static final Logger logger = Logger.getLogger(WhiteListConfig.class);

    /**
     * 白名单
     */
    private static final String WHITE_LIST = "whiteList";

    /**
     * 放在header中的时间戳
     */
    private static final String TIMESTAMP = "phicomm-timestamp";

    /**
     * 放在header中的key值
     */
    private static final String KEY = "phicomm-key";

    /**
     * 放在header中的token值
     */
    private static final String TOKEN = "phicomm-token";

    /**
     * 放在header中的userId
     */
    private static final String USER_ID = "phicomm-userId";

    /**
     * 放在header中的appId
     */
    private static final String APP_ID = "phicomm-appId";

    /**
     * 授权在redis中的前缀
     */
    private static final String AUTH_PREFIX = "atk_";

    /**
     * 时间戳超过10分钟就认为错误
     */
    private static final long TIMEOUT = 600000;
    
    /**
     * token 有效时间：7天
     */
    private static final long TOKEN_VALID_TIME = 604800000;

    /**
     * 白名单
     */
    private Set<String> whiteList;

    /**
     * 忽略授权
     */
    private boolean ignoreAuthorization = false;

    private SqlSessionFactory sqlSessionFactory;

    private JedisCluster jedisCluster;

    private NTPServer ntpServer;

    private String defaultAppId;

    /**
     * 获取配置文件地址，解析xml
     *
     * @param filterConfig 过滤器配置
     */
    WhiteListConfig(FilterConfig filterConfig) {
        logger.info(String.format("%s filter is init.", filterConfig.getFilterName()));
        String filePath = filterConfig.getInitParameter(WHITE_LIST);
        logger.info(String.format("filter white file is %s.", filePath));
        //下面方法得到的URL对空格，特殊字符(%,#,[]等)和中文进行了编码处理(若路径中含有特殊字符，则会获取不到对应的文件)
        URL whiteListFile = Resources.getResource(filePath);
        // 解析xml文件
        initFilterUrl(whiteListFile);
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        sqlSessionFactory = webApplicationContext.getBean(SqlSessionFactory.class);
        jedisCluster = webApplicationContext.getBean(JedisCluster.class);
        ntpServer = webApplicationContext.getBean(NTPServerImp.class);
        CommonPropertiesConfig commonPropertiesConfig = webApplicationContext.getBean(CommonPropertiesConfig.class);
        defaultAppId = webApplicationContext.getBean(CommonPropertiesConfig.class).getAppId();
        logger.info(String.format("defaultAppId is %s.", defaultAppId));
        String environment = commonPropertiesConfig.getEnvironment();
        logger.info(String.format("environment is %s.", environment));
        //ignoreAuthorization = !"prod".equals(environment);
        logger.info(String.format("ignoreAuthorization = %s.", ignoreAuthorization));
    }

    /**
     * 获取需要过滤的接口
     */
    private void initFilterUrl(URL whiteListFile) {
        whiteList = Sets.newHashSet();
        SAXReader reader = new SAXReader();
        Document document;
        File configFile = new File(whiteListFile.getFile().replaceAll("%20", " "));
        try {
            document = reader.read(configFile);
        } catch (DocumentException e) {
            logger.error(ExceptionUtil.getErrorMessage(e));
            return;
        }
        String ignore = document.getRootElement().attributeValue("ignore");
        if (ignore != null && "true".equals(ignore)) {
            ignoreAuthorization = true;
            logger.info("authorization is ignored.");
            return;
        }
        Iterator iterator1 = document.getRootElement().elementIterator();
        while (iterator1.hasNext()) {
            Element element = (Element) iterator1.next();
            String tag = element.getName();
            Iterator iterator2 = element.elementIterator();
            while (iterator2.hasNext()) {
                Element element2 = (Element) iterator2.next();
                String url = element2.getTextTrim();
                if (!Strings.isNullOrEmpty(url)) {
                    whiteList.add(url);
                    logger.info(String.format("filter url : %s-%s.", tag, url));
                }
            }
        }
    }

    /**
     * 是否需要过滤掉这个请求
     * timestamp
     * key
     * token
     * userId
     */
    boolean filter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (ignoreAuthorization) {
            return true;
        }
        String url = httpServletRequest.getServletPath();
        if (url == null) {
            return false;
        }
        // 白名单不需要验证
        if (url.startsWith("/resources/")
                || url.startsWith("/druid")
                || url.startsWith("/test")
                || url.startsWith("/h5")
                || url.endsWith("redirect")
                || whiteList.contains(url)) {
            return true;
        }
        long timestamp;
        int key;
        String userId;
        String appId;
        String token;
        try {
            timestamp = Long.valueOf(httpServletRequest.getHeader(TIMESTAMP));
            key = Integer.valueOf(httpServletRequest.getHeader(KEY));
            userId = httpServletRequest.getHeader(USER_ID);
            appId = httpServletRequest.getHeader(APP_ID);
            token = httpServletRequest.getHeader(TOKEN);
        } catch (Exception e) {
            return errorResponse(httpServletResponse, 1003, "need authorization");
        }
        if (Strings.isNullOrEmpty(appId)) {
            appId = defaultAppId;
        }
        long currentTime = ntpServer.getTime();
        // 判断时间是否超时
        if (Math.abs(currentTime - timestamp) > TIMEOUT
                && Math.abs(System.currentTimeMillis() - timestamp) > TIMEOUT) {
            logger.info(String.format("currentTime = %d, timestamp = %d", currentTime, timestamp));
            ntpServer.synchronizeTime();
            return errorResponse(httpServletResponse, 1004, "timestamp timeout.");
        }
        // 判断key是否正确
        if (SecurityUtil.getKey(timestamp) != key) {
            return errorResponse(httpServletResponse, 1005, "key error.");
        }
        // 验证token
        return checkToken(userId, appId, token, currentTime) ||
                errorResponse(httpServletResponse, 1006, "token error.");
    }

    /**
     * 验证token是否正确
     *
     * @param userId 用户id
     * @param token  token
     * @return 是否正确
     */
    private boolean checkToken(String userId, String appId, String token, long currentTime) {
        String key = AUTH_PREFIX + userId + "_" + appId;
        String target = jedisCluster.get(key);
        String currentToken;
        long expireTime;
        if (Strings.isNullOrEmpty(target)) {
            logger.info("cache miss.");
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                TokenValidateMapper tokenMapper = sqlSession.getMapper(TokenValidateMapper.class);
                TokenBean tokenBean = tokenMapper.getToken(userId, appId);
                if (tokenBean != null) {
                    currentToken = tokenBean.getToken();
                    expireTime = tokenBean.getUpdateTime().getTime() + TOKEN_VALID_TIME;
                } else {
                    logger.info("token miss.");
                    return false;
                }
            }
            jedisCluster.set(key, currentToken + "_" + expireTime);
            return token.equals(currentToken) && currentTime < expireTime;
        }
        String[] splits = target.split("_");
        currentToken = splits[0];
        expireTime = Long.valueOf(splits[1]);
        return token.equals(currentToken) && currentTime < expireTime;
    }

    /**
     * 时间戳过期
     *
     * @param httpServletResponse response
     * @return 是否拦截返回无效
     */
    private boolean errorResponse(HttpServletResponse httpServletResponse, int status, String description) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("description", description);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = null;
        try {
            printWriter = httpServletResponse.getWriter();
            printWriter.append(JSON.toJSONString(jsonObject));
        } catch (Exception e) {
            logger.info(ExceptionUtil.getErrorMessage(e));
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
        return false;
    }

}

