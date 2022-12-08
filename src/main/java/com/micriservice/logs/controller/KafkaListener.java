package com.micriservice.logs.controller;

import com.micriservice.logs.model.dto.SendLogsKafka;
import com.micriservice.logs.service.LogsService;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@EnableKafka
public class KafkaListener {
  private final LogsService logsService;
    @org.springframework.kafka.annotation.KafkaListener(topics = "logsTopic",groupId = "myGroup",containerFactory = "logsKafkaListenerContainerFactory")
    void listener(SendLogsKafka sendLogsKafka) {
      System.out.println(sendLogsKafka);
      logsService.addLog(sendLogsKafka);
    }
}
