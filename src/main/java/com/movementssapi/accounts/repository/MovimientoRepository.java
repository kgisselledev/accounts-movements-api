package com.movementssapi.accounts.repository;

import com.movementssapi.accounts.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaClienteIdAndFechaBetween(Long Id, Date fechaInicio, Date fechaFin);
}
