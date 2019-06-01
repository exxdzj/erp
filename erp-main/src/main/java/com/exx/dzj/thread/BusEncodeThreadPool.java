package com.exx.dzj.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author
 * @Date 2019/5/31 0031 15:56
 * @Description 业务数据编程规则---线程池
 */
public class BusEncodeThreadPool {

    /**
     * 获取Java虚拟机的可用的处理器数，最佳线程个数，处理器数*2, 根据实际情况调整
     */
    int curSystemThreads = Runtime.getRuntime().availableProcessors();

    private ScheduledExecutorService threadPool = new ScheduledThreadPoolExecutor(curSystemThreads,
            new BasicThreadFactory.Builder().namingPattern("busencode-threadpool-%d").daemon(true).build());

    /**
     * 静态内部类，实现单例  确保线程安全
     */
    private static class Singleton {
        private static BusEncodeThreadPool instance;

        static {
            instance = new BusEncodeThreadPool();
        }

        public static BusEncodeThreadPool newInstance() {
            return instance;
        }
    }

    public static BusEncodeThreadPool getInstance() {
        return Singleton.newInstance();
    }

    /**
     * 初始化便捷方法
     */
    public static void init() {
        getInstance();
    }
}
