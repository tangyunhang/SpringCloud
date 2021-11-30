package com.abc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
//将MQ与生产者通过消息管道相绑定
@EnableBinding(Sink.class)//3.1后此注解废弃
public class SomeConsumer2 {

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void priMessages(Object msg){
       System.out.println(msg);
    }
}
