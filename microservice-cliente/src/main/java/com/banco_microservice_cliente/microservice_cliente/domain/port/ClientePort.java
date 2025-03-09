package com.banco_microservice_cliente.microservice_cliente.domain.port;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClientePort {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
}
