package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableHystrixDashboard //开启Dashboard仪表盘功能
@SpringCloudApplication
public class Applicationturbine8200 {

    public static void main(String[] args) {
        SpringApplication.run(Applicationturbine8200.class, args);
    }

}
