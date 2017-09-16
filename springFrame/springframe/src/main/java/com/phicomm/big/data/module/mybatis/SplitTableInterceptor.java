package com.phicomm.big.data.module.mybatis;


import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.phicomm.big.data.config.CommonPropertiesConfig;
import com.phicomm.big.data.utils.ReflectUtil;

/**
 * 分表的拦截器
 * <p>
 * Created by yufei.liu on 2017/6/19.
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class SplitTableInterceptor implements Interceptor, ApplicationContextAware {

    private static final Logger logger = Logger.getLogger(SplitTableInterceptor.class);

    private static final String SQL_REFLECT_NAME = "sql";

    /**
     * 拦截的参数名字
     */
    private static final String[] filterParamNameList = new String[]{
            "splitTableFlag"
    };

    /**
     * 拦截的表名字
     */
    private static final String[] filterTableNameList = new String[]{
            "mysql_test",
    };

    /**
     * 分表的大小
     */
    private static final int[] splitTableSizeList = new int[]{
            2
    };

    private int BALANCE_MEASURE_INFO_SPLIT_TABLE_THRESHOLD;

    /**
     * 拦截sql，对sql进行重构
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        int tableOrder = isNeedSplitTable(sql);
        if (tableOrder < 0) {
            return invocation.proceed();
        }
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject instanceof HashMap) {
            HashMap map = (HashMap) parameterObject;
            Object splitTableId = map.get(filterParamNameList[tableOrder]);
            if (splitTableId != null) {
                int tableIndex = getSplitTableIndex(splitTableId, tableOrder);
                String newTableName = getSplitTableName(tableOrder, tableIndex);
                String newSql = sql.replace(filterTableNameList[tableOrder], newTableName);
                ReflectUtil.setFieldValue(boundSql, SQL_REFLECT_NAME, newSql);
                logger.debug(String.format("splitTableId = %s, index = %d, sql = %s",
                        splitTableId, tableIndex, newSql.replaceAll("\\s+", " ")));
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 判断是否需要分表
     */
    private int isNeedSplitTable(String sql) {
        for (int i = 0; i < filterTableNameList.length; i++) {
            if (sql.contains(filterTableNameList[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获得分表的索引
     *
     * @param splitTableId 分表id
     * @param index        索引
     * @return 获得分片id
     */
    private int getSplitTableIndex(Object splitTableId, int index) {
        switch (index) {
            case 0: {
                Long memberId = (Long) splitTableId;
                return (int) (memberId % splitTableSizeList[index]);
            }
            case 1:
            case 2: {
                String userId = (String) splitTableId;
                return getSplitTableIndex(userId, splitTableSizeList[index]);
            }
            case 3: {
                Long memberId = (Long) splitTableId;
                if (memberId < BALANCE_MEASURE_INFO_SPLIT_TABLE_THRESHOLD) {
                    return 0;
                }
                return (int) (memberId % 50);
            }
        }
        throw new Error();
    }

    /**
     * 使用: DJB Hash算法
     * 将userId散列到0 - splitTableSize - 1的一个值
     */
    private int getSplitTableIndex(String userId, int splitTableSize) {
        long hash = 5381;
        for (int i = 0; i < userId.length(); i++) {
            hash = ((hash << 5) + hash) + userId.charAt(i);
        }
        return (int) (Math.abs(hash) % splitTableSize);
    }

    /**
     * 获得分表的名字
     *
     * @param tableIndex 索引
     * @return 分表的名字
     */
    private String getSplitTableName(int tableOrder, int tableIndex) {
        System.out.println(tableOrder);
        System.out.println(tableIndex);
        if (tableOrder == 3 && tableIndex == 0) {
            return filterTableNameList[tableOrder];
        }
        return filterTableNameList[tableOrder] + "_" + tableIndex;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CommonPropertiesConfig commonPropertiesConfig = applicationContext.getBean(CommonPropertiesConfig.class);
        String environment = commonPropertiesConfig.getEnvironment();
        logger.info(String.format("environment = %s", environment));
        BALANCE_MEASURE_INFO_SPLIT_TABLE_THRESHOLD = environment.equalsIgnoreCase("prod") ? 85000 : 6365480;
        logger.info(String.format("BALANCE_MEASURE_INFO_SPLIT_TABLE_THRESHOLD = %d", BALANCE_MEASURE_INFO_SPLIT_TABLE_THRESHOLD));
    }
}


