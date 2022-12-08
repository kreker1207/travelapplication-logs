package com.micriservice.logs.service;

import com.micriservice.logs.entity.Logs;
import com.micriservice.logs.entity.UserRequest;
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
    public void addLog(List<UserRequest> userRequest){
        System.out.println("saving");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        LocalDateTime timestamp = LocalDateTime.now();
        Logs logs = new Logs().setId(id).setTime(timestamp).setUserRequest(userRequest);
        System.out.println("Id of log:" + id);
        logsRepository.save(logs);
    }
}
