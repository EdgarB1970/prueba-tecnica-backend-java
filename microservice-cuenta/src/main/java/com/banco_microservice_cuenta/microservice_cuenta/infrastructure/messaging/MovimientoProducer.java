package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.messaging;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Movimiento;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovimientoProducer {
    private final KafkaTemplate<String, Movimiento> kafkaTemplate;

    public MovimientoProducer(KafkaTemplate<String, Movimiento> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMovimiento(Movimiento movimiento){
        kafkaTemplate.send("movimientos", movimiento);
    }
}
