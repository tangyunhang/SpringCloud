package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy //开启zuul代理模式
@SpringBootApplication
public class ApplicationZuulGray8800 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationZuulGray8800.class, args);
    }

}
