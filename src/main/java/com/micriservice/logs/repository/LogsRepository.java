package com.micriservice.logs.repository;

import com.micriservice.logs.entity.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends MongoRepository<Logs, String> {
}
