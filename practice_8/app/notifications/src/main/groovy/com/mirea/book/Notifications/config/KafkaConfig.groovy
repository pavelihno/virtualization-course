package com.mirea.book.notifications

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.*
import org.springframework.kafka.core.*
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig

@Configuration
public class KafkaConfig {

    @Value('${spring.kafka.bootstrap-servers}')
    private String bootstrapServers

    @Value('spring.kafka.consumer.group-id')
    private String groupId

    @Bean
    public ConsumerFactory<String, BookDTO> consumerFactory() {
        Map<String, Object> props = new HashMap<>()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class)

        props.put(JsonDeserializer.TRUSTED_PACKAGES, '*')

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(BookDTO.class, false))
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BookDTO> factory = new ConcurrentKafkaListenerContainerFactory<>()
        
        factory.setConsumerFactory(consumerFactory())
        
        return factory
    }
}
