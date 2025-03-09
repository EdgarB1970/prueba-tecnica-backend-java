package com.banco_reporte.banco_reporte.application;

import com.banco_reporte.banco_reporte.infrastructure.dto.MovimientoDTO;
import com.banco_reporte.banco_reporte.infrastructure.repository.ReporteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @Test
    public void testActualizarReporteConMovimiento(){
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(1L);
        when(reporteRepository.existsById(1L)).thenReturn(false);
        reporteService.actualizarReporteConMovimiento(movimientoDTO);
        verify(reporteRepository, times(1)).save(movimientoDTO);
    }
}
