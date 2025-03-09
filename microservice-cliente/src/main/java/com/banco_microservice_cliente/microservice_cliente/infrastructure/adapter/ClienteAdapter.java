package com.banco_microservice_cliente.microservice_cliente.infrastructure.adapter;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Cliente;
import com.banco_microservice_cliente.microservice_cliente.domain.port.ClientePort;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.ClienteEntity;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.mapper.ClienteMapper;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteAdapter implements ClientePort {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteAdapter(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public List<Cliente> findAll() {
        return  clienteRepository.findAll().stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDomain);
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        return clienteMapper.toDomain(clienteRepository.save(clienteEntity));
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}