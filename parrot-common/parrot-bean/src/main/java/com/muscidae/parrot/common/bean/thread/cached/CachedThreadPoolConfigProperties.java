package com.muscidae.parrot.common.bean.thread.cached;

import com.muscidae.parrot.common.bean.Properties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author muscidae
 * @date 2019/8/12 11:20
 * @description 缓存线程池
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = CachedThreadPoolConfigProperties.PREFIX)
public class CachedThreadPoolConfigProperties implements Properties {

    public static final String PREFIX = "thread-pool.cached";

    private Integer corePoolSize;

    private Integer maxPoolSize;

    private String threadNamePrefix;

    private RejectedExecutionHandler rejectedExecution;

}
