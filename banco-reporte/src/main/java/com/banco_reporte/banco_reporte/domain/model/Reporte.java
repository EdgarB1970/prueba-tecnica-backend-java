package com.banco_reporte.banco_reporte.domain.model;

import com.banco_reporte.banco_reporte.infrastructure.dto.CuentaDTO;
import com.banco_reporte.banco_reporte.infrastructure.dto.MovimientoDTO;
import lombok.Data;

import java.util.List;

@Data
public class Reporte {
    private Long clienteId;
    private List<CuentaDTO> cuentas;
    private List<MovimientoDTO> movimientos;
}
