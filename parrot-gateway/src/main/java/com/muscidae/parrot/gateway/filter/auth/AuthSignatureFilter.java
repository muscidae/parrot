package com.muscidae.parrot.gateway.filter.auth;

import com.muscidae.parrot.common.constant.CommonConst;
import com.muscidae.parrot.gateway.filter.DefaultFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author muscidae
 * @date 2020/4/8 18:08
 * @copyright ©2020
 * @description 鉴权过滤器
 */
@Slf4j
@Component
public class AuthSignatureFilter implements GatewayFilter, DefaultFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authorization = exchange.getRequest().getHeaders().getFirst(CommonConst.AUTHORIZATION);
        if (null == authorization || authorization.isEmpty())  //非空
            return defaultComplete(exchange, HttpStatus.UNAUTHORIZED);


        return defaultFilter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
