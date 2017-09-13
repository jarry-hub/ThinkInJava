package com.phicomm.big.data.module.dds;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防止mybatis因为异常没有执行clean方法，导致数据源有问题
 *
 * @author yufei.liu
 */
public class DataSourceFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CustomerContextHolder.clearCustomerType();
        return super.preHandle(request, response, handler);
    }

}