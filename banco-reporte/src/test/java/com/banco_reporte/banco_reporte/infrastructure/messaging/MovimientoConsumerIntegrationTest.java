package com.banco_reporte.banco_reporte.infrastructure.messaging;

import com.banco_reporte.banco_reporte.infrastructure.dto.MovimientoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
public class MovimientoConsumerIntegrationTest {

    @Container
    private static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.0"));

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry){
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaTemplate<String, MovimientoDTO> kafkaTemplate;

    @Autowired
    private MovimientoConsumer movimientoConsumer;

    @Test
    public void testRecibirMovimiento(){
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(1L);

        kafkaTemplate.send("movimientos", movimientoDTO);

        assertNotNull(movimientoConsumer);
    }
}
