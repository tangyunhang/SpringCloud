package com.abc.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface CustomSource {
    String CHANEL_NAME="output2";
    @Output(CustomSource.CHANEL_NAME)
    MessageChannel output();
}
