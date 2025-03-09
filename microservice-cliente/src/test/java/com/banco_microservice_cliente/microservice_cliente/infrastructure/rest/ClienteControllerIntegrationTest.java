package com.banco_microservice_cliente.microservice_cliente.infrastructure.rest;

import com.banco_microservice_cliente.microservice_cliente.domain.model.Cliente;
import com.banco_microservice_cliente.microservice_cliente.domain.model.Persona;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.ClienteEntity;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.entity.PersonaEntity;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.mapper.ClienteMapper;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.mapper.PersonaMapper;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.repository.ClienteRepository;
import com.banco_microservice_cliente.microservice_cliente.infrastructure.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PersonaMapper personaMapper;

    @BeforeEach
    public void setup() {
        clienteRepository.deleteAll();
        personaRepository.deleteAll();
    }

    @Test
    public void testCreateCliente() throws Exception {
        Persona persona = new Persona();
        persona.setNombre("Juan Perez");
        persona.setIdentificacion("123456789");

        PersonaEntity personaEntity = personaMapper.toEntity(persona);
        personaRepository.save(personaEntity);

        Cliente cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setContrasena("1234");
        cliente.setEstado(true);

        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        clienteRepository.save(clienteEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"persona\":{\"id\":" + personaEntity.getId() + "},\"contrasena\":\"1234\",\"estado\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clienteId").exists());
    }

    @Test
    public void testGetClienteById() throws Exception {
        Persona persona = new Persona();
        persona.setNombre("Maria Lopez");
        persona.setIdentificacion("987654321");
        PersonaEntity personaEntity = personaMapper.toEntity(persona);
        personaRepository.save(personaEntity);

        Cliente cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setContrasena("5678");
        cliente.setEstado(true);
        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        clienteRepository.save(clienteEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/{id}", clienteEntity.getClienteId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(clienteEntity.getClienteId()));
    }
}