package com.banco_microservice_cuenta.microservice_cuenta.application;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;
import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Movimiento;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.CuentaPort;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.MovimientoPort;
import com.banco_microservice_cuenta.microservice_cuenta.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    private static final Logger logger = LoggerFactory.getLogger(MovimientoService.class);
    private final MovimientoPort movimientoPort;
    private final CuentaPort cuentaPort;

    public MovimientoService(MovimientoPort movimientoPort, CuentaPort cuentaPort) {
        this.movimientoPort = movimientoPort;
        this.cuentaPort = cuentaPort;
    }

    public List<Movimiento> findAll() {
        logger.info("Buscando todos los movimientos");
        return movimientoPort.findAll();
    }

    public Optional<Movimiento> findById(Long id) {
        logger.info("Buscando movimiento con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de movimiento no válido: {}", id);
            throw new CustomException("ID de movimiento no válido");
        }
        return movimientoPort.findById(id);
    }

    public Movimiento save(Movimiento movimiento) {
        logger.info("Guardando movimiento: {}", movimiento);
        if (movimiento == null) {
            logger.error("Movimiento no puede ser nulo");
            throw new CustomException("Movimiento no puede ser nulo");
        }

        validarSaldo(movimiento);
        actualizarSaldoCuenta(movimiento);

        return movimientoPort.save(movimiento);
    }

    private void validarSaldo(Movimiento movimiento) {
        Long cuentaId = movimiento.getCuentaId();
        Cuenta cuenta = cuentaPort.findById(cuentaId)
                .orElseThrow(() -> new CustomException("Cuenta no encontrada"));

        if ("Retiro".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            if (cuenta.getSaldoInicial() < movimiento.getValor()) {
                logger.error("Saldo no disponible para el retiro en la cuenta: {}", cuentaId);
                throw new CustomException("Saldo no disponible");
            }
        }
    }

    private void actualizarSaldoCuenta(Movimiento movimiento) {
        Long cuentaId = movimiento.getCuentaId();
        Cuenta cuenta = cuentaPort.findById(cuentaId)
                .orElseThrow(() -> new CustomException("Cuenta no encontrada"));

        if ("Deposito".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());
        } else if ("Retiro".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            cuenta.setSaldoInicial(cuenta.getSaldoInicial() - movimiento.getValor());
        }

        cuentaPort.save(cuenta);
    }

    public void deleteById(Long id) {
        logger.info("Eliminando movimiento con ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("ID de movimiento no válido: {}", id);
            throw new CustomException("ID de movimiento no válido");
        }
        movimientoPort.deleteById(id);
    }
}