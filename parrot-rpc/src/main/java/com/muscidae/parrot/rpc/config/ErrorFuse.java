package com.muscidae.parrot.rpc.config;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author muscidae
 * @date 2020/4/10 17:52
 * @copyright ©2020
 * @description Feign全局异常处理
 */
@Slf4j
@Configuration
public class ErrorFuse implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("methodKey: {}, reason is {}", methodKey, response.toString());
        return FeignException.errorStatus(methodKey, response);
    }

}
