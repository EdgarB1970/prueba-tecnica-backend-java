package com.banco_reporte.banco_reporte.infrastructure.messaging;

import com.banco_reporte.banco_reporte.application.ReporteService;
import com.banco_reporte.banco_reporte.infrastructure.dto.MovimientoDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MovimientoConsumer {

    private final ReporteService reporteService;

    public MovimientoConsumer(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @KafkaListener(topics = "movimientos", groupId = "banco-group")
    public void recibirMovimiento(MovimientoDTO movimientoDTO){
        reporteService.actualizarReporteConMovimiento(movimientoDTO);
    }
}
