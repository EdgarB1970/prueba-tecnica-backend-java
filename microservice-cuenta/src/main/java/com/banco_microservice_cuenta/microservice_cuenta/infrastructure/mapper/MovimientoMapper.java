package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.mapper;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Movimiento;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.dto.MovimientoDTO;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity.MovimientoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    MovimientoDTO toDTO(MovimientoEntity movimientoEntity);
    MovimientoEntity toEntity(MovimientoDTO movimientoDTO);

    Movimiento toDomain(MovimientoEntity movimientoEntity);
    MovimientoEntity toEntity(Movimiento movimiento);
}
