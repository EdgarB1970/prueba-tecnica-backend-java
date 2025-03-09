package com.banco_reporte.banco_reporte.infrastructure.client;

import com.banco_reporte.banco_reporte.infrastructure.dto.CuentaDTO;
import com.banco_reporte.banco_reporte.infrastructure.dto.MovimientoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Component
public class CuentaServiceClient {

    private final WebClient webClient;

    public CuentaServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public List<CuentaDTO> obtenerCuentasPorCliente(Long clienteId) {
        return webClient.get()
                .uri("/api/cuentas/cliente/{clienteId}", clienteId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CuentaDTO>>() {})
                .block();
    }

    public List<MovimientoDTO> obtenerMovimientosPorCuentaYFecha(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin) {
        return webClient.get()
                .uri("/api/movimientos/cuenta/{cuentaId}?fechaInicio={fechaInicio}&fechaFin={fechaFin}", cuentaId, fechaInicio, fechaFin)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<MovimientoDTO>>() {})
                .block();
    }
}