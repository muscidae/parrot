package com.muscidae.parrot.gateway.filter.swagger;

import com.muscidae.parrot.gateway.config.swagger.SwaggerProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @author muscidae
 * @date 2019/9/28 14:38
 * @copyright ©2019
 * @description Swagger请求头过滤
 */
@Component
public class SwaggerHeaderFilter<C> extends AbstractGatewayFilterFactory<C> {

    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    @Override
    public GatewayFilter apply(C config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if (!StringUtils.endsWithIgnoreCase(path, SwaggerProvider.API_URI))
                return chain.filter(exchange);
            return chain.filter(exchange.mutate().request(
                        request.mutate().header(HEADER_NAME, path.substring(0, path.lastIndexOf(SwaggerProvider.API_URI))
                    ).build()
                ).build()
            );
        };
    }
}