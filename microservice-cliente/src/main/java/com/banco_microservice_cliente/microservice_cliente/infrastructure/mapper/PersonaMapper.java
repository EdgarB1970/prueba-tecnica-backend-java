package com.banco_microservice_cliente.microservice_cliente.infrastructure.mapper;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Persona;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.dto.PersonaDTO;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.PersonaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDTO toDTO(PersonaEntity personaEntity);
    PersonaEntity toEntity(PersonaDTO personaDTO);

    Persona toDomain(PersonaEntity personaEntity);
    PersonaEntity toEntity(Persona persona);
}
