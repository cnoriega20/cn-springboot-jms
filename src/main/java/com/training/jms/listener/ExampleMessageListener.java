package com.training.jms.listener;

import com.training.jms.config.JmsConfig;
import com.training.jms.model.ExampleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Slf4j
@Component
public class ExampleMessageListener {

    private Object ExampleMessage;

    @JmsListener(destination = JmsConfig.QUEUE)
    public void listen(@Payload ExampleMessage exampleMessage,
                       @Headers MessageHeaders headers, Message message) {
        log.info("Got a message");
        log.info("example Message",exampleMessage);


    }
}
