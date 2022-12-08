package com.micriservice.logs.configuration;

import com.micriservice.logs.entity.UserRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public Map<String,Object> consumerConfig(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"myGroup");
        return props;
    }
    @Bean
    public ConsumerFactory<String, UserRequest> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }
    @Bean
    public KafkaListenerContainerFactory <ConcurrentMessageListenerContainer<String,UserRequest>>factory(){
        ConcurrentKafkaListenerContainerFactory<String,UserRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return  factory;
    }
}
