package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic movimientosTopic(){
        return TopicBuilder.name("movimientos")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic clientesTopic(){
        return TopicBuilder.name("clientes")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
