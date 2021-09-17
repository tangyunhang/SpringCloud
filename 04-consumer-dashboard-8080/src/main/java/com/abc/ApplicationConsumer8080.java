package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //开启feign服务
@EnableCircuitBreaker//开启熔断器
@EnableHystrixDashboard //开启Dashboard仪表盘功能
@SpringBootApplication
public class ApplicationConsumer8080 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumer8080.class, args);
    }

}
