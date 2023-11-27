package com.movementssapi.accounts.service;

import com.movementssapi.accounts.dto.MovimientoDTO;
import com.movementssapi.accounts.model.Movimiento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    Movimiento crearMovimiento(Movimiento movimiento);

    List<Movimiento> getAllMovimientos();

    Optional<Movimiento> obtenerMovimientoPorId(Integer id);

    boolean existeMovimientoPorId(Integer id);

    Movimiento updateMovimiento(Movimiento movimiento);

    void deleteMovimiento(Integer id);

    Movimiento realizarMovimiento(Integer idCuenta, BigDecimal monto, String tipoMovimiento, MovimientoDTO movimientoRes, LocalDate fecha);

    List<Movimiento> obtenerMovimientosEnRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin);
}
