package com.banco_microservice_cliente.microservice_cliente.infrastructure.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long clienteId;
    private PersonaDTO persona;
    private String contrasena;
    private Boolean estado;
}
