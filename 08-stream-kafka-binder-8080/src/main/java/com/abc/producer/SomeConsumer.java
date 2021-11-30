package com.abc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
//将MQ与生产者通过消息管道相绑定
@EnableBinding(Sink.class)//3.1后此注解废弃
public class SomeConsumer {

    @Autowired
    @Qualifier(Sink.INPUT)
    private SubscribableChannel channel;

    @PostConstruct
    public void priMessages(){
       channel.subscribe(message -> System.out.println(new String((byte[])message.getPayload())));
    }
}
