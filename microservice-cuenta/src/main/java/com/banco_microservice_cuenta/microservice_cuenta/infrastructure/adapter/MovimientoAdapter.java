package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.adapter;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Movimiento;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.MovimientoPort;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity.MovimientoEntity;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.mapper.MovimientoMapper;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoAdapter  implements MovimientoPort {
    private final MovimientoRepository movimientoRepository;
    private  final MovimientoMapper movimientoMapper;

    public MovimientoAdapter(MovimientoRepository movimientoRepository, MovimientoMapper movimientoMapper) {
        this.movimientoRepository = movimientoRepository;
        this.movimientoMapper = movimientoMapper;
    }


    @Override
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll().stream()
                .map(movimientoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return movimientoRepository.findById(id)
                .map(movimientoMapper::toDomain);
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        MovimientoEntity movimientoEntity = movimientoMapper.toEntity(movimiento);
        return movimientoMapper.toDomain(movimientoRepository.save(movimientoEntity));
    }

    @Override
    public void deleteById(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> findByCuentaIdInAndFechaBetween(List<Long> cuentaIds, LocalDate fechaInicio, LocalDate fechaFin) {
        return movimientoRepository.findByCuenta_NumeroCuentaInAndFechaBetween(cuentaIds, fechaInicio,fechaFin).stream()
                .map(movimientoMapper::toDomain)
                .collect(Collectors.toList());
    }
}