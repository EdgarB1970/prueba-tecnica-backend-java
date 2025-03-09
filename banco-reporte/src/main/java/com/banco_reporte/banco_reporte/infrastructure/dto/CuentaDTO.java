package com.banco_reporte.banco_reporte.infrastructure.dto;

import lombok.Data;

@Data
public class CuentaDTO {
    private Long numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Long clienteId;
}