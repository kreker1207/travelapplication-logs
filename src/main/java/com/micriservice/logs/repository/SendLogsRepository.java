package com.micriservice.logs.repository;

import com.micriservice.logs.model.dto.SendLogsKafka;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendLogsRepository extends MongoRepository<SendLogsKafka,String> {

}
