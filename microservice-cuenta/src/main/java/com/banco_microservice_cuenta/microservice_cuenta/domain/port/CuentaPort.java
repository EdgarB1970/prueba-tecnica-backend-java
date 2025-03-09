package com.banco_microservice_cuenta.microservice_cuenta.domain.port;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaPort {
    List<Cuenta> findAll();
    Optional<Cuenta> findById(Long id);
    Cuenta save(Cuenta cuenta);
    void deleteById(Long id);
    List<Cuenta> findByClienteId(Long clienteId);
}
