package com.training.jms.sender;

import com.training.jms.config.JmsConfig;
import com.training.jms.model.ExampleMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class Sender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        log.info("Sending a message...");

        ExampleMessage message = ExampleMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Sending from ExampleMessage")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.QUEUE, message);
        log.info("Cesar-> Message Sent..");
    }
}
