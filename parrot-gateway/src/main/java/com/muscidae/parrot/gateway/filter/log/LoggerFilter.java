package com.muscidae.parrot.gateway.filter.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;


/**
 * @author muscidae
 * @date 2019/4/28 23:23
 * @description 请求参数过滤器 (@Deprecated, 性能损耗)
 */
@Slf4j
//@Component
@Deprecated
public class LoggerFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                ServerHttpRequest request = exchange.getRequest();
                log.debug("ip:『{}』", Objects.requireNonNull(request.getRemoteAddress()).getAddress());
                log.debug("path:『{}』", request.getPath());
                log.debug("params:『{}』", request.getQueryParams());
            }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
