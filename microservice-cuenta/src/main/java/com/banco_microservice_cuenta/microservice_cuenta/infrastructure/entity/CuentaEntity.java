package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cuenta")
@NoArgsConstructor
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCuenta;

    @Column(nullable = false)
    private String tipoCuenta;

    @Column(nullable = false)
    private Double saldoInicial = 0.0;

    @Column(nullable = false)
    private Boolean estado = true;

    @Column(nullable = false)
    private Long clienteId;
}