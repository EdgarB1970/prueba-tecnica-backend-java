package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.adapter;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.CuentaPort;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity.CuentaEntity;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.mapper.CuentaMapper;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaAdapter implements CuentaPort {
    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaAdapter(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll().stream()
                .map(cuentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return cuentaRepository.findById(id)
                .map(cuentaMapper::toDomain);
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        CuentaEntity cuentaEntity = cuentaMapper.toEntity(cuenta);
        return cuentaMapper.toDomain(cuentaRepository.save(cuentaEntity));
    }

    @Override
    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> findByClienteId(Long clienteId){
        return cuentaRepository.findByClienteId(clienteId).stream()
                .map(cuentaMapper::toDomain)
                .collect(Collectors.toList());
    }
}