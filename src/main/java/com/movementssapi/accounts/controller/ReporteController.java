package com.movementssapi.accounts.controller;

import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> obtenerMovimientosEnRangoDeFechas(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {

        List<Movimiento> movimientos = movimientoService.obtenerMovimientosEnRangoDeFechas(fechaInicio, fechaFin);


        return movimientos;
    }
}
