package com.phicomm.big.data.module.authorization;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权认证过滤器
 * Created by yufei.liu
 */
public class PhicommAuthorizationFilter implements Filter {

    /**
     * 日志
     */
    private static final Logger logger = Logger.getLogger(PhicommAuthorizationFilter.class);

    /**
     * 白名单
     */
    private WhiteListConfig whiteListConfig;

    /**
     * 授权接口白名单
     *
     * @param filterConfig 文件位置
     * @throws ServletException 会抛出初始化异常
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        String filterName = filterConfig.getFilterName();
        logger.info(String.format("filter config file is %s.", filterName));
        whiteListConfig = new WhiteListConfig(filterConfig);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        boolean ok = whiteListConfig.filter(httpServletRequest, httpServletResponse);
        if (!ok) {
            return;
        }
        // 过滤器链接着走
        chain.doFilter(request, response);
    }

    public void destroy() {
        // do nothing
    }
}
