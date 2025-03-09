package com.banco_microservice_cliente.microservice_cliente.application;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Cliente;
import com.banco_microservice_cliente.microservice_cliente.domain.port.ClientePort;
import com.banco_microservice_cliente.microservice_cliente.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClientePort clientePort;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testFindAll() {
        Cliente cliente1 = new Cliente();
        cliente1.setClienteId(1L);
        Cliente cliente2 = new Cliente();
        cliente2.setClienteId(2L);
        when(clientePort.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));
        List<Cliente> clientes = clienteService.findAll();

        assertEquals(2, clientes.size());
        verify(clientePort, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        when(clientePort.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getClienteId());
        verify(clientePort, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        when(clientePort.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getClienteId());
        verify(clientePort, times(1)).save(cliente);
    }

    @Test
    public void testDeleteById() {
        clienteService.deleteById(1L);
        verify(clientePort, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByIdWithInvalidId() {
        assertThrows(CustomException.class, () -> clienteService.findById(-1L));
    }
}