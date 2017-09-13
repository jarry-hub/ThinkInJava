package com.phicomm.big.data.module.dds;

import com.google.common.base.Strings;

public class CustomerContextHolder {

    /**
     * 主要的数据源
     */
    private static final String MAIN_DATA_SOURCE = "maxscale";

    /**
     * 附属数据源
     */
    private static final String ASSISTANT_DATA_SOURCE = "assistant";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 如果没有设置，那就取默认的数据源maxscale，否则选择该数据源
     */
    static String getCustomerType() {
        String datasource = contextHolder.get();
        if (Strings.isNullOrEmpty(datasource)) {
            return MAIN_DATA_SOURCE;
        }
        return datasource;
    }

    public static void selectAssistantDataSource() {
        contextHolder.set(ASSISTANT_DATA_SOURCE);
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}  