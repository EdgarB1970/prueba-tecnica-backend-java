package com.banco_microservice_cuenta.microservice_cuenta.domain.port;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoPort {
    List<Movimiento> findAll();
    Optional<Movimiento> findById(Long id);
    Movimiento save(Movimiento movimiento);
    void deleteById(Long id);
    List<Movimiento> findByCuentaIdInAndFechaBetween(List<Long> cuentaIds, LocalDate fechaInicio, LocalDate fechaFin);
}
