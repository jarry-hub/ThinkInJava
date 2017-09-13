package com.phicomm.big.data.module.ntp;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ntp服务器实现类
 * <p>
 * Created by yufei.liu 
 */
@Component
public class NTPServerImp implements NTPServer {

    private static final Logger logger = Logger.getLogger(NTPServerImp.class);

    private static final String NTP_SERVER = "202.120.2.101";

    private AtomicLong time;

    private ScheduledExecutorService synchronizeTimeThread = Executors.newSingleThreadScheduledExecutor();

    private ScheduledExecutorService workThread = Executors.newSingleThreadScheduledExecutor();

    /**
     * 同步时间
     */
    @PostConstruct
    public void init() {
        time = new AtomicLong();
        time.set(System.currentTimeMillis());
        synchronizeTimeThread.scheduleAtFixedRate(new SynchronizeTimeRunnable(), 0, 1, TimeUnit.MINUTES);
        workThread.scheduleAtFixedRate(new WorkRunnable(), 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 获取服务器时间
     *
     * @return 服务器时间
     */
    @Override
    public long getTime() {
        return time.get();
    }

    /**
     * 同步时间
     * 优先使用网络时间，如果网络时间没有获取到那就使用系统时间
     */
    public void synchronizeTime() {
        try {
            NTPUDPClient timeClient = new NTPUDPClient();
            timeClient.setDefaultTimeout(4000);
            InetAddress timeServerAddress = InetAddress.getByName(NTP_SERVER);
            TimeInfo timeInfo = timeClient.getTime(timeServerAddress);
            TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();
            time.set(timeStamp.getTime());
        } catch (Exception ignore) {
            time.set(System.currentTimeMillis());
        }
    }

    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        synchronizeTimeThread.shutdown();
        workThread.shutdown();
        logger.info("NTP server shutdown.");
    }

    private class SynchronizeTimeRunnable implements Runnable {

        @Override
        public void run() {
            synchronizeTime();
        }

    }

    private class WorkRunnable implements Runnable {

        @Override
        public void run() {
            time.addAndGet(1000);
        }

    }
}
