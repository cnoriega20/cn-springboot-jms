package com.training.jms.listener;

import com.training.jms.config.JmsConfig;
import com.training.jms.model.ExampleMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExampleMessageListener {

    private Object ExampleMessage;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.QUEUE)
    public void listen(@Payload ExampleMessage exampleMessage,
                       @Headers MessageHeaders headers, Message message) {
        log.info("Got a message");
        log.info("example Message", exampleMessage);


    }

    @JmsListener(destination = JmsConfig.SEND_RECEIVE_QUEUE)
    public void listenForCustomMessage(@Payload ExampleMessage exampleMessage,
                                       @Headers MessageHeaders headers, Message message) throws JMSException {

        com.training.jms.model.ExampleMessage payloadMsg = com.training.jms.model.ExampleMessage.builder()
                .id(UUID.randomUUID())
                .message("Sending from listenForCustomMessage...")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);
    }
}
