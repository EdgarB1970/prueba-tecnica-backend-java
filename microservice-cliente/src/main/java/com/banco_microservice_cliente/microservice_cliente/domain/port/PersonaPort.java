package com.banco_microservice_cliente.microservice_cliente.domain.port;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaPort {
    List<Persona> findAll();
    Optional<Persona> findById(Long id);
    Persona save(Persona persona);
    void deleteById(Long id);
}
