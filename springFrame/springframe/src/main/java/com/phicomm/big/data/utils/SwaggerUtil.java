package com.phicomm.big.data.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.phicomm.big.data.module.swagger.SwaggerUIFilter;

/**
 * swagger工具类
 *
 * Created by yufei.liu
 */
public class SwaggerUtil {

    private SwaggerUtil() {}

    /**
     * 判断是否登陆
     */
    public static boolean isLogin() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attrs.getRequest().getSession();
        Object object = session.getAttribute(SwaggerUIFilter.SWAGGER_LOGIN_FLAG);
        return object != null;
    }

}
