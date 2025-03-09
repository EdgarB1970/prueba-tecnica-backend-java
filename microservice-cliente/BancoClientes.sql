CREATE DATABASE banco_clientes;

CREATE TABLE persona (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50),
    edad INT,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    cliente_id BIGSERIAL PRIMARY KEY,
    persona_id BIGINT REFERENCES persona(id) ON DELETE CASCADE,
    contraseña VARCHAR(255) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);