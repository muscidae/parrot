package com.muscidae.parrot.common.bean.cache.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;


/**
 * @author muscidae
 * @date 2019/1/27 21:26
 * @description Redis配置类
 */
@EnableCaching
@Configuration
public class RedisConfig {

    /**
     * @author muscidae
     * @date 2019/8/8 22:21
     * @description 使用FastJson序列化
     * @param
     * @return org.springframework.data.redis.serializer.RedisSerializer<java.lang.Object>
     */
    @Bean
    public RedisSerializer<Object> jsonRedisSerializer() {
        return new FastJsonRedisSerializer<>(Object.class);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory, RedisSerializer<Object> fastJsonRedisSerializer) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, RedisSerializer<Object> fastJsonRedisSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(fastJsonRedisSerializer);
        template.setValueSerializer(fastJsonRedisSerializer);
        return template;
    }


}
