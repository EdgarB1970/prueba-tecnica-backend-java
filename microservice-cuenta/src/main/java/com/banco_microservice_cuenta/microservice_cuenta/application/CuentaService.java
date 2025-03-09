package com.banco_microservice_cuenta.microservice_cuenta.application;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.CuentaPort;
import com.banco_microservice_cuenta.microservice_cuenta.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    private static final Logger logger = LoggerFactory.getLogger(CuentaService.class);
    private final CuentaPort cuentaPort;

    public CuentaService(CuentaPort cuentaPort) {
        this.cuentaPort = cuentaPort;
    }

    public List<Cuenta> findAll() {
        logger.info("Buscando todas las cuentas");
        return cuentaPort.findAll();
    }

    public Optional<Cuenta> findById(Long id) {
        logger.info("Buscando cuenta con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de cuenta no válido: {}", id);
            throw new CustomException("ID de cuenta no válido");
        }
        return cuentaPort.findById(id);
    }

    public Cuenta save(Cuenta cuenta) {
        logger.info("Guardando cuenta: {}", cuenta);
        if (cuenta == null) {
            logger.error("Cuenta no puede ser nula");
            throw new CustomException("Cuenta no puede ser nula");
        }
        return cuentaPort.save(cuenta);
    }

    public void deleteById(Long id) {
        logger.info("Eliminando cuenta con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de cuenta no válido: {}", id);
            throw new CustomException("ID de cuenta no válido");
        }
        cuentaPort.deleteById(id);
    }
}
