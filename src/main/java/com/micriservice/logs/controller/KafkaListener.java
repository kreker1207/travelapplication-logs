package com.micriservice.logs.controller;

import com.micriservice.logs.entity.UserRequest;
import com.micriservice.logs.service.LogsService;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class KafkaListener {
    private final LogsService logsService;
    @org.springframework.kafka.annotation.KafkaListener(topics = "logsTopic",groupId = "myGroup")
    void listener(@Payload List<UserRequest> data){
        System.out.println("Listener got message "+ data);
        logsService.addLog(data);
    }
}
