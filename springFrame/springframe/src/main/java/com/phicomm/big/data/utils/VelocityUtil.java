package com.phicomm.big.data.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * velocity工具类
 *
 * @author yufei.liu
 */
public class VelocityUtil {

    /**
     * 格式化时间
     */
    public static String formatDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.format("%d月%d日 %d:%d",
                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

}

