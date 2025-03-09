package com.banco_microservice_cliente.microservice_cliente.infrastructure.repository;

import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
