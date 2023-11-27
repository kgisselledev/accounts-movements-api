package com.movementssapi.accounts.repository;


import com.movementssapi.accounts.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;


public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
