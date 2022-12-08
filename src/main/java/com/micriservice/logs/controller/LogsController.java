package com.micriservice.logs.controller;

import com.micriservice.logs.entity.Logs;
import com.micriservice.logs.service.LogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class LogsController {
    private final LogsService service;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Logs> getLogs(){
        return service.getLogs();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Logs getLog(@PathVariable String id){
        return service.getLog(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLog(@PathVariable String id){
        service.deleteLog(id);
    }

}
