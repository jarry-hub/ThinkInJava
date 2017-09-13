package com.phicomm.big.data.utils;

/**
 * 安全工具类
 * Created by yufei.liu
 */
public class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * 获得key
     *
     * @param timestamp 时间戳
     * @return hash值
     */
    public static int getKey(long timestamp) {
        long t = timestamp;
        byte[] data = new byte[8];
        for (int i = 0; i < 8; i++) {
            byte value = (byte) (t & 0xff);
            t = t >>> 8;
            data[7 - i] = (byte) (value ^ 0x3c);
        }
        // 分组h
        int[] sumArray = new int[4];
        for (int i = 0; i < 4; i++) {
            sumArray[i] = (data[i] & 0xff) << 8 + (data[7 - i] & 0xff);
        }
        return Math.abs(sumArray[0] + sumArray[1] + sumArray[2] + sumArray[3]);
    }

    /**
     * 获得key
     *
     * @param timestamp 时间戳
     * @return hash值
     */
    public static int getKeyForLiJiang(long timestamp) {
        byte[] data = new byte[8];
        for (int i = 0; i < 8; i++) {
            byte value = (byte) (timestamp & 0xff);
            timestamp = timestamp >>> 8;
            data[7 - i] = (byte) (value ^ 0x3b);
        }
        // 分组h
        int[] sumArray = new int[4];
        for (int i = 0; i < 4; i++) {
            sumArray[i] = (data[i] & 0xff) << 8 + (data[7 - i] & 0xff);
        }
        return Math.abs(sumArray[0] + sumArray[1] + sumArray[2] + sumArray[3]);
    }

}

