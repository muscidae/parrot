package com.muscidae.parrot.gateway.filter.auth;

import com.muscidae.parrot.gateway.filter.DefaultConfig;
import com.muscidae.parrot.gateway.filter.DefaultFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author muscidae
 * @date 2019/4/28 23:19
 * @description 鉴权过滤器
 */
@Component
public class AuthSignatureGatewayFilterFactory
        extends AbstractGatewayFilterFactory<AuthSignatureGatewayFilterFactory.Config> implements DefaultFilter {

    public AuthSignatureGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        if (config.isEnable())
            return new AuthSignatureFilter();
        return this::defaultFilter;
    }

    @Getter
    @Setter
    static class Config extends DefaultConfig {

    }
}
