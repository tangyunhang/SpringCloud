package com.abc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;



@Component
//将MQ与生产者通过消息管道相绑定
@EnableBinding({Source.class,CustomSource.class})//3.1后此注解废弃
public class SomeProducer {
    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel channel;

    @Autowired
    @Qualifier(CustomSource.CHANEL_NAME)
    private MessageChannel channelName;

    public String sendMessages(String message){
        //通过消息管道发消息，即消息写入管道，再通过管道写入MQ
        channel.send(MessageBuilder.withPayload(message).build());
        //通过消息管道发消息，即消息写入管道，再通过管道写入MQ
        channelName.send(MessageBuilder.withPayload(message).build());
        return message;
    }
}
