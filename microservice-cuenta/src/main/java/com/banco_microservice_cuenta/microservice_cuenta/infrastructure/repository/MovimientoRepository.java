package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.repository;

import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
    List<MovimientoEntity> findByCuenta_NumeroCuentaInAndFechaBetween(List<Long> cuentaIds, LocalDate fechaInicio, LocalDate fechaFin);
}
