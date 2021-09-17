package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
//import org.springframework.cloud.netflix.turbine.EnableTurbine;

//@EnableTurbine //开启turbine聚合功能
@EnableHystrixDashboard //开启hustrix仪表盘
@SpringCloudApplication
public class ApplicationTurbine7000 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTurbine7000.class, args);
    }

}
