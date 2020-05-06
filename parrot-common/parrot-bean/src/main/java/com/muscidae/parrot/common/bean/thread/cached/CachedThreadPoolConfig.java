package com.muscidae.parrot.common.bean.thread.cached;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author muscidae
 * @date 2019/8/12 11:37
 * @copyright ©2019
 * @description 缓存线程池配置类
 */
@EnableAsync
@Configuration
public class CachedThreadPoolConfig {

    @Bean
    public Executor cachedThreadPool(CachedThreadPoolConfigProperties cachedThreadPoolConfigProperties) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(cachedThreadPoolConfigProperties.getCorePoolSize());
        taskExecutor.setMaxPoolSize(cachedThreadPoolConfigProperties.getMaxPoolSize());
        taskExecutor.setThreadNamePrefix(cachedThreadPoolConfigProperties.getThreadNamePrefix());
        taskExecutor.setRejectedExecutionHandler(cachedThreadPoolConfigProperties.getRejectedExecution());
        taskExecutor.initialize();
        return Executors.newCachedThreadPool(taskExecutor);
    }

}
