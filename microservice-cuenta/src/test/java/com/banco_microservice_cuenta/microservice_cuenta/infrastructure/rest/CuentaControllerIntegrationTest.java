package com.banco_microservice_cuenta.microservice_cuenta.infrastructure.rest;

import com.banco_microservice_cuenta.microservice_cuenta.domain.model.Cuenta;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.entity.CuentaEntity;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.mapper.CuentaMapper;
import com.banco_microservice_cuenta.microservice_cuenta.infrastructure.repository.CuentaRepository;
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
public class CuentaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @BeforeEach
    public void setup() {
        cuentaRepository.deleteAll();
    }

    @Test
    public void testCreateCuenta() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1L);
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        CuentaEntity cuentaEntity = cuentaMapper.toEntity(cuenta);
        cuentaRepository.save(cuentaEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numeroCuenta\":1,\"tipoCuenta\":\"Ahorro\",\"saldoInicial\":1000.0,\"estado\":true,\"clienteId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCuenta").value(1L));
    }

    @Test
    public void testGetCuentaById() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(1L);
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        CuentaEntity cuentaEntity = cuentaMapper.toEntity(cuenta);
        cuentaRepository.save(cuentaEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cuentas/{id}", cuentaEntity.getNumeroCuenta()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value(cuentaEntity.getNumeroCuenta()));
    }
}