package com.banco_microservice_cliente.microservice_cliente.infrastructure.repository;

import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<ClienteEntity, Long> {
}