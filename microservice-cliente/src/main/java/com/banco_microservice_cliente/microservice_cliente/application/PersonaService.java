package com.banco_microservice_cliente.microservice_cliente.application;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Persona;
import com.banco_microservice_cliente.microservice_cliente.domain.port.PersonaPort;
import com.banco_microservice_cliente.microservice_cliente.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    private static  final Logger logger= LoggerFactory.getLogger(PersonaService.class);
    private final PersonaPort personaPort;

    public PersonaService(PersonaPort personaPort) {
        this.personaPort = personaPort;
    }

    public List<Persona> findAll() {
        logger.info("Buscando todas las personas");
        return personaPort.findAll();
    }

    public Optional<Persona> findById(Long id) {
        logger.info("Buscando persona con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de persona no válido: {}", id);
            throw new CustomException("ID de persona no válido");
        }
        return personaPort.findById(id);
    }

    public Persona save(Persona persona) {
        logger.info("Guardando persona: {}", persona);
        if (persona == null) {
            logger.error("Persona no puede ser nula");
            throw new CustomException("Persona no puede ser nula");
        }
        return personaPort.save(persona);
    }

    public void deleteById(Long id) {
        logger.info("Eliminando persona con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de persona no válido: {}", id);
            throw new CustomException("ID de persona no válido");
        }
        personaPort.deleteById(id);
    }
}
