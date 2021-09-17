package com.abc.configure;


import com.abc.balance.CustmRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DepartConfigure {

    //自定义Ribbon负载均衡策略
    @Bean
    public IRule LoadBalancedCustmRule(){
        List<Integer> excludePorts = new ArrayList<>();
        excludePorts.add(8082);
        return new CustmRule(excludePorts);
    }
    //指定Ribbon使用随机算法策略
//      @Bean
//      public IRule LoadBalancedRule(){
//        return new RandomRule();
//      }

  //开启消息端负载均衡功能,默认采用轮询
      @LoadBalanced
      @Bean
      public RestTemplate restTemplate(){
        return new RestTemplate();
      }
}
