package com.micriservice.logs.configuration;

import com.micriservice.logs.model.dto.SendLogsKafka;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public ConsumerFactory<String, SendLogsKafka> logsConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.*");
        props.put(JsonDeserializer.TYPE_MAPPINGS, "SendLogsKafka:com.micriservice.logs.model.dto.SendLogsKafka");

        return new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            new JsonDeserializer<>(SendLogsKafka.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SendLogsKafka> logsKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SendLogsKafka> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(logsConsumerFactory());
        return factory;
    }
}
