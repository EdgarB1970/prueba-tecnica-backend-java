package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.repository;

import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
    List<CuentaEntity> findByClienteId(Long clienteId);
}
