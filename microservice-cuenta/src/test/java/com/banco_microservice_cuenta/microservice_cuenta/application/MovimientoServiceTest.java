package com.banco_microservice_cuenta.microservice_cuenta.application;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;
import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Movimiento;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.CuentaPort;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.MovimientoPort;
import com.banco_microservice_cuenta.microservice_cuenta.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MovimientoServiceTest {
    @Mock
    private MovimientoPort movimientoPort;

    @Mock
    private CuentaPort cuentaPort;

    @InjectMocks
    private MovimientoService movimientoService;

    @Test
    public void testSaveMovimientoConSaldoInsuficiente(){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1L);
        cuenta.setSaldoInicial(100.0);
        when(cuentaPort.findById(1L)).thenReturn(Optional.of(cuenta));

        Movimiento movimiento = new Movimiento();
        movimiento.setCuentaId(1L);
        movimiento.setTipoMovimiento("Retiro");
        movimiento.setValor(150.0);

        CustomException exception= assertThrows(CustomException.class, ()->{
            movimientoService.save(movimiento);
        });

        assertEquals("Saldo no disponible", exception.getMessage());
    }
}
