package com.movementssapi.accounts.service;

import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.response.MovimientoResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MovimientoService {
    MovimientoResponse saveMovimiento(Movimiento movimiento);

    Movimiento getMovimientoById(Long Id);

    List<Movimiento> getAllMovimientos();

    Movimiento updateMovimiento(Long Id, Movimiento movimientoDetails);

    void deleteMovimiento(Long Id);

    List<Movimiento> getMovimientosPorClienteYFecha(Long Id, Date fechaInicio, Date fechaFin);
}
