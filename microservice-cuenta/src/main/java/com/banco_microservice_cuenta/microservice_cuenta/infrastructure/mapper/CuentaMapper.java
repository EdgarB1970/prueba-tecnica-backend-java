package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.mapper;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.dto.CuentaDTO;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity.CuentaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaDTO toDTO(CuentaEntity cuentaEntity);
    CuentaEntity toEntity(CuentaDTO cuentaDTO);

    Cuenta toDomain(CuentaEntity cuentaEntity);
    CuentaEntity toEntity(Cuenta cuenta);
}