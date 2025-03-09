package com.banco_reporte.banco_reporte.infrastructure.repository;

import com.banco_reporte.banco_reporte.infrastructure.dto.MovimientoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<MovimientoDTO, Long> {
}
