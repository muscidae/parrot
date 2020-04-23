package com.muscidae.parrot.gateway.filter.time;

import com.muscidae.parrot.gateway.filter.DefaultFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author muscidae
 * @date 2019/4/28 23:40
 * @description 时间段过滤器
 */
@Slf4j
@Component
public class TimeGatewayFilterFactory<C>
        extends AbstractGatewayFilterFactory<C> implements DefaultFilter {

    private static final String START_TIME = "StartTime";

    @Override
    public GatewayFilter apply(C config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
            return chain.filter(exchange).then(Mono.fromRunnable(
                    () -> {
                        Long startTime = exchange.getAttribute(START_TIME);
                        long endTime = System.currentTimeMillis() - (null == startTime ? 0 : startTime);
                        log.info(exchange.getRequest().getURI().toString() + ":" + endTime + "ms");
                    }
            ));
        };
    }

}
