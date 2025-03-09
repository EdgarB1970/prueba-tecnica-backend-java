package com.banco_microservice_cuenta.microservice_cuenta.application;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;
import com.banco_microservice_cuenta.microservice_cuenta.domain.port.CuentaPort;
import com.banco_microservice_cuenta.microservice_cuenta.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

    @Mock
    private CuentaPort cuentaPort;

    @InjectMocks
    private CuentaService cuentaService;

    @Test
    public void testFindAll() {
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setNumeroCuenta(1L);
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta(2L);
        when(cuentaPort.findAll()).thenReturn(Arrays.asList(cuenta1, cuenta2));

        List<Cuenta> cuentas = cuentaService.findAll();
        assertEquals(2, cuentas.size());
        verify(cuentaPort, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1L);
        when(cuentaPort.findById(1L)).thenReturn(Optional.of(cuenta));

        Optional<Cuenta> resultado = cuentaService.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getNumeroCuenta());
        verify(cuentaPort, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1L);
        when(cuentaPort.save(cuenta)).thenReturn(cuenta);
        Cuenta resultado = cuentaService.save(cuenta);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getNumeroCuenta());
        verify(cuentaPort, times(1)).save(cuenta);
    }

    @Test
    public void testDeleteById() {
        cuentaService.deleteById(1L);
        verify(cuentaPort, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByIdWithInvalidId() {
        assertThrows(CustomException.class, () -> cuentaService.findById(-1L));
    }
}