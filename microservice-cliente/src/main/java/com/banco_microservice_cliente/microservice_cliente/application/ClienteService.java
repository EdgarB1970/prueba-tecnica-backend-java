package com.banco_microservice_cliente.microservice_cliente.application;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Cliente;
import com.banco_microservice_cliente.microservice_cliente.domain.port.ClientePort;
import com.banco_microservice_cliente.microservice_cliente.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private static  final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final ClientePort clientePort;

    public ClienteService(ClientePort clientePort) {
        this.clientePort = clientePort;
    }

    public List<Cliente> findAll() {
        logger.info("Buscando todos los clientes");
        return clientePort.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        logger.info("Buscando cliente con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de cliente no válido: {}", id);
            throw new CustomException("ID de cliente no válido");
        }
        return clientePort.findById(id);
    }

    public Cliente save(Cliente cliente) {
        logger.info("Guardando cliente: {}", cliente);
        if (cliente == null) {
            logger.error("Cliente no puede ser nulo");
            throw new CustomException("Cliente no puede ser nulo");
        }
        return clientePort.save(cliente);
    }

    public void deleteById(Long id) {
        logger.info("Eliminando cliente con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de cliente no válido: {}", id);
            throw new CustomException("ID de cliente no válido");
        }
        clientePort.deleteById(id);
    }
}
