package com.movementssapi.accounts.repository;

import com.movementssapi.accounts.model.AuditoriaMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface auditoriaMovimientoRepository extends JpaRepository<AuditoriaMovimiento, Integer> {
}
