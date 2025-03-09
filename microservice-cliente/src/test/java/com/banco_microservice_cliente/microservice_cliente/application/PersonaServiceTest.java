package com.banco_microservice_cliente.microservice_cliente.application;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Persona;
import com.banco_microservice_cliente.microservice_cliente.domain.port.PersonaPort;
import com.banco_microservice_cliente.microservice_cliente.exception.CustomException;
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
public class PersonaServiceTest {

    @Mock
    private PersonaPort personaPort;

    @InjectMocks
    private PersonaService personaService;

    @Test
    public void testFindAll() {
        Persona persona1 = new Persona();
        persona1.setId(1L);
        Persona persona2 = new Persona();
        persona2.setId(2L);
        when(personaPort.findAll()).thenReturn(Arrays.asList(persona1, persona2));

        List<Persona> personas = personaService.findAll();
        assertEquals(2, personas.size());
        verify(personaPort, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Persona persona = new Persona();
        persona.setId(1L);
        when(personaPort.findById(1L)).thenReturn(Optional.of(persona));

        Optional<Persona> resultado = personaService.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(personaPort, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Persona persona = new Persona();
        persona.setId(1L);
        when(personaPort.save(persona)).thenReturn(persona);

        Persona resultado = personaService.save(persona);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(personaPort, times(1)).save(persona);
    }

    @Test
    public void testDeleteById() {
        personaService.deleteById(1L);
        verify(personaPort, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByIdWithInvalidId() {
        // Verificar que se lanza una excepción con un ID no válido
        assertThrows(CustomException.class, () -> personaService.findById(-1L));
    }
}