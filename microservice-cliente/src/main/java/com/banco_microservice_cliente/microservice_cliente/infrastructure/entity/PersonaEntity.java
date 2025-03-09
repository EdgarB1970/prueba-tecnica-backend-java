package com.banco_microservice_cliente.microservice_cliente.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "persona")
@NoArgsConstructor
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String genero;

    @Column
    private Integer edad;

    @Column(nullable = false, unique = true)
    private String identificacion;

    @Column
    private String direccion;

    @Column
    private String telefono;
}
