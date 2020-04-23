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
        //配置核心线程数
        taskExecutor.setCorePoolSize(cachedThreadPoolConfigProperties.getCorePoolSize());
        //配置最大线程数
        taskExecutor.setMaxPoolSize(cachedThreadPoolConfigProperties.getMaxPoolSize());
        //配置线程池中的线程的名称前缀
        taskExecutor.setThreadNamePrefix(cachedThreadPoolConfigProperties.getThreadNamePrefix());
        //设置线程策略
        taskExecutor.setRejectedExecutionHandler(cachedThreadPoolConfigProperties.getPolicy().getRejectedExecutionHandler());
        //执行线程初始化
        taskExecutor.initialize();
        return Executors.newCachedThreadPool(taskExecutor);
    }

}
