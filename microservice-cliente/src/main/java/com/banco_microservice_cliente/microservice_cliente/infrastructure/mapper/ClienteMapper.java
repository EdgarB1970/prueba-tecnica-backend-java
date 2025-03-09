package com.banco_microservice_cliente.microservice_cliente.infrastructure.mapper;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Cliente;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.dto.ClienteDTO;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(ClienteEntity clienteEntity);
    ClienteEntity toEntity(ClienteDTO clienteDTO);

    Cliente toDomain(ClienteEntity clienteEntity);
    ClienteEntity toEntity(Cliente cliente);
}
