package com.banco_microservice_cliente.microservice_cliente.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long clienteId;
    private Persona persona;
    private String contrasena;
    private Boolean estado;
}
