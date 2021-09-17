package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard //开启Dashboard仪表盘功能
@SpringBootApplication
public class ApplicationDashboard9000 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDashboard9000.class, args);
    }

}
