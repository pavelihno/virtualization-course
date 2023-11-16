package com.mirea.book.catalog

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.*
import org.springframework.kafka.core.*

@Configuration
public class KafkaConfig {

    @Value(value='${spring.kafka.bootstrap-servers}')
    private String bootstrapAddress

    @Bean
    public ProducerFactory<String, BookDTO> producerFactory() {
        Map<String, Object> config = new HashMap<>()
        
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
        
        return new DefaultKafkaProducerFactory<>(config)
    }

    @Bean
    public KafkaTemplate<String, BookDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory())
    }
}