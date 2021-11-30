package com.abc.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;


@Component
//将MQ与生产者通过消息管道相绑定
@EnableBinding(Sink.class)//3.1后此注解废弃
public class SomeConsumer3 {
    @StreamListener(Sink.INPUT)
    public void priMessages(Object msg){
       System.out.println(msg);
    }
}
