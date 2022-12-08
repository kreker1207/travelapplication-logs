package com.micriservice.logs.service;

import com.micriservice.logs.model.entity.Logs;
import com.micriservice.logs.model.dto.SendLogsKafka;
import com.micriservice.logs.repository.LogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogsService {
    private final LogsRepository logsRepository;
    public List<Logs> getLogs(){
        return logsRepository.findAll();
    }
    public Logs getLog(String id){
        return logsRepository.findById(id).orElseThrow(()->{throw new EntityNotFoundException("Log was not found by id");
        });
    }
    public void deleteLog(String id){
        if(!logsRepository.existsById(id)){
            throw new EntityNotFoundException("Log was not found by id");
        }
        logsRepository.deleteById(id);
    }
    public void addLog(SendLogsKafka sendLogsKafka){
        System.out.println("saving");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        LocalDateTime timestamp = LocalDateTime.now();
        Logs logs = new Logs().setId(id).setTime(timestamp).setSendLogsKafka(sendLogsKafka);
        System.out.println("Id of log:" + id);
        logsRepository.save(logs);
    }
}
