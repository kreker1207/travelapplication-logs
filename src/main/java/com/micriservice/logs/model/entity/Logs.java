package com.micriservice.logs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document("logs")
@Accessors(chain = true)
public class Logs {
    @Id
    String id;
    @JsonProperty("Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy MMM dd")
    LocalDateTime time;
    @JsonProperty("Logs")
    SendLogsKafka sendLogsKafka;
}
