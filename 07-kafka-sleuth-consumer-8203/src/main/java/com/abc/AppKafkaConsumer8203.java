package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //开启feign服务
@EnableCircuitBreaker//开启熔断器
@SpringBootApplication
public class AppKafkaConsumer8203 {

    public static void main(String[] args) {
        SpringApplication.run(AppKafkaConsumer8203.class, args);
    }

}
