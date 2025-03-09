package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.dto;

import lombok.Data;

@Data
public class CuentaDTO {
    private Long numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Long clienteId;
}
