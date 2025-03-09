package com.banco_microservice_cliente.microservice_cliente.infrastructure.adapter;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Persona;
import com.banco_microservice_cliente.microservice_cliente.domain.port.PersonaPort;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.PersonaEntity;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.mapper.PersonaMapper;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaAdapter implements PersonaPort {
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    public PersonaAdapter(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll().stream()
                .map(personaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id)
                .map(personaMapper::toDomain);
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity personaEntity = personaMapper.toEntity(persona);
        return personaMapper.toDomain(personaRepository.save(personaEntity));
    }

    @Override
    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }
}