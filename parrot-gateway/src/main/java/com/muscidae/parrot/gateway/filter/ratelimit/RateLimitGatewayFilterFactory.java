package com.muscidae.parrot.gateway.filter.ratelimit;

import com.muscidae.parrot.gateway.filter.DefaultFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author muscidae
 * @date 2020/4/8 00:29
 * @copyright ©2020
 * @description 限流过滤器, 检测CPU是否超过设置的阈值决定是否开启限流
 */
@Slf4j
@Component
@AllArgsConstructor
public class RateLimitGatewayFilterFactory<C>
        extends AbstractGatewayFilterFactory<C> implements DefaultFilter {

    private final MetricsEndpoint metricsEndpoint;

    private static final String METRIC_NAME = "system.cpu.usage";
    private static final double MAX_USAGE = 0.50D;

    @Override
    public GatewayFilter apply(C config) {
        return (exchange, chain) -> {
            Double systemCpuUsage = metricsEndpoint.metric(METRIC_NAME,null)
                    .getMeasurements()
                    .stream()
                    .filter(Objects::nonNull)
                    .findFirst()
                    .map(MetricsEndpoint.Sample::getValue)
                    .filter(Double::isFinite)
                    .orElse(0.0D);
            boolean isOpenRateLimit = systemCpuUsage > MAX_USAGE;
            log.debug("System.cpu.usage: 『{}』, isOpenRateLimit: 『{}』", systemCpuUsage, isOpenRateLimit);
            if (isOpenRateLimit)
                return defaultComplete(exchange, HttpStatus.TOO_MANY_REQUESTS);
            else
                return defaultFilter(exchange, chain);
        };
    }


}
