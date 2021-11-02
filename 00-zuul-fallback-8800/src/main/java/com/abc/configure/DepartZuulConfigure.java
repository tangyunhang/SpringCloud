package com.abc.configure;

import com.abc.balance.ZuulRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//@Configuration
public class DepartZuulConfigure {

//    //修改为随机策略
    @Bean
    public IRule zuulLoadBalancedRule(){
        return new RandomRule();
    }
//    @Bean
//    public IRule zuulLoadBalancedZuulRule(){
//        return new ZuulRule(Arrays.asList(8183));
//    }
}
