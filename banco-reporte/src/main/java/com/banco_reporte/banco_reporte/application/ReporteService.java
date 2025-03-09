package com.banco_reporte.banco_reporte.application;

import com.banco_reporte.banco_reporte.domain.model.Reporte;
import com.banco_reporte.banco_reporte.exception.CustomException;
import com.banco_reporte.banco_reporte.infrastructure.client.CuentaServiceClient;
import com.banco_reporte.banco_reporte.infrastructure.dto.CuentaDTO;
import com.banco_reporte.banco_reporte.infrastructure.dto.MovimientoDTO;
import com.banco_reporte.banco_reporte.infrastructure.repository.ReporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    private static final Logger logger = LoggerFactory.getLogger(ReporteService.class);
    private final CuentaServiceClient cuentaServiceClient;
    private final ReporteRepository reporteRepository;

    public ReporteService(CuentaServiceClient cuentaServiceClient, ReporteRepository reporteRepository) {
        this.cuentaServiceClient = cuentaServiceClient;
        this.reporteRepository = reporteRepository;
    }

    public Reporte generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        logger.info("Generando reporte para el cliente ID: {} entre {} y {}", clienteId, fechaInicio, fechaFin);

        if (clienteId == null || clienteId <= 0) {
            logger.error("ID de cliente no válido: {}", clienteId);
            throw new CustomException("ID de cliente no válido");
        }

        if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
            logger.error("Rango de fechas no válido: {} - {}", fechaInicio, fechaFin);
            throw new CustomException("Rango de fechas no válido");
        }

        List<CuentaDTO> cuentas = cuentaServiceClient.obtenerCuentasPorCliente(clienteId);

        List<MovimientoDTO> movimientos = new ArrayList<>();
        for (CuentaDTO cuenta : cuentas) {
            List<MovimientoDTO> movimientosCuenta = cuentaServiceClient.obtenerMovimientosPorCuentaYFecha(
                    cuenta.getNumeroCuenta(), fechaInicio, fechaFin
            );
            movimientos.addAll(movimientosCuenta);
        }

        Reporte reporte = new Reporte();
        reporte.setClienteId(clienteId);
        reporte.setCuentas(cuentas);
        reporte.setMovimientos(movimientos);

        return reporte;
    }

    public void actualizarReporteConMovimiento(MovimientoDTO movimientoDTO) {
        logger.info("Actualizando reporte con movimiento: {}", movimientoDTO);

         boolean movimientoExiste = reporteRepository.existsById(movimientoDTO.getId());

        if (!movimientoExiste) {
            reporteRepository.save(movimientoDTO);
            logger.info("Movimiento agregado al reporte: {}", movimientoDTO);
        } else {
            reporteRepository.save(movimientoDTO);
            logger.info("Movimiento actualizado en el reporte: {}", movimientoDTO);
        }
    }
}