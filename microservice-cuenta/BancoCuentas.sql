CREATE DATABASE banco_cuentas;

CREATE TABLE cuenta (
    numero_cuenta BIGSERIAL PRIMARY KEY,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DOUBLE PRECISION DEFAULT 0.0,
    estado BOOLEAN DEFAULT TRUE,
    cliente_id BIGINT NOT NULL  -- Referencia al cliente (no es FK)
);

CREATE TABLE movimiento (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    saldo DOUBLE PRECISION NOT NULL,
    cuenta_id BIGINT REFERENCES cuenta(numero_cuenta) ON DELETE CASCADE
);