package com.muscidae.parrot.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author muscidae
 * @date 2020/3/14 19:44
 * @copyright ©2020
 * @description 默认拦截器
 */
public interface DefaultFilter {

    /**
     * @author muscidae
     * @date 2020/3/14 19:50
     * @description 设置放回状态码进行拦截处理
     * @param exchange ServerWebExchange
	 * @param status Http状态码
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    default Mono<Void> defaultComplete(ServerWebExchange exchange, HttpStatus status){
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    /**
     * @author muscidae
     * @date 2020/3/14 19:55
     * @description 拦截放行
     * @param exchange ServerWebExchange
	 * @param chain GatewayFilterChain
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    default Mono<Void> defaultFilter(ServerWebExchange exchange, GatewayFilterChain chain){
        return chain.filter(exchange);
    }

}
