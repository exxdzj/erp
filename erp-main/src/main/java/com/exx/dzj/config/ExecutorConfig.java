package com.exx.dzj.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description
 * @author yangyun
 * @date 2019/6/5 0005
 * @return
 */
@Configuration
@EnableAsync
@PropertySource("classpath:executor-config.properties")
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Value("${asyn.executor.thread.core_pool_size}")
    private int corePoolSize;

    @Value("${asyn.executor.thread.max_pool_size}")
    private int maxPoolSize;

    @Value("${asyn.executor.thread.queue_capacity}")
    private int queueCapacity;

    @Value("${asyn.executor.thread.name.prefix}")
    private String namePrefix;

    @Value("${asyn.executor.thread.keep_alive_seconds}")
    private int keepAliveSeconds;

    @Bean("asyncSaleExecutr")
    public AsyncTaskExecutor asyncSaleExecutr (){
        logger.info("start asyncSaleExecutr");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(namePrefix);

        // 其他三种拒绝策略都会丢弃任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.setKeepAliveSeconds(keepAliveSeconds);
//        executor.setAllowCoreThreadTimeOut(true);

        // 初始化
        executor.initialize();

        return executor;
    }
}
