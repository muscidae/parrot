package com.muscidae.parrot.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients( basePackages = {"com.muscidae.parrot.rpc"} )
@SpringBootApplication(
    exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class },
    scanBasePackages = { "com.muscidae.parrot.rpc", "com.muscidae.parrot.gateway" }
)
public class ParrotGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParrotGatewayApplication.class, args);
    }

}

