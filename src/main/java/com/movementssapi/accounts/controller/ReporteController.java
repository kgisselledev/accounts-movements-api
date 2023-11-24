package com.movementssapi.accounts.controller;

import com.movementssapi.accounts.dto.ReporteEstCuentaDTO;
import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.service.CuentaService;
import com.movementssapi.accounts.service.CuentaServiceImpl;
import com.movementssapi.accounts.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaService cuentaService;
    @GetMapping
    public ResponseEntity<ReporteEstCuentaDTO> generarReporte(
            @RequestParam("Id") Long Id,
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaFin) {

        List<Cuenta> cuentas = cuentaService.getCuentasPorCliente(Id);
        // Lógica para generar el reporte
        List<Movimiento> movimientos = movimientoService.getMovimientosPorClienteYFecha(Id, fechaInicio, fechaFin);

        // A partir de los movimientos, construir el objeto DTO del reporte
        ReporteEstCuentaDTO reporte = ((CuentaServiceImpl) cuentaService).construirReporte(cuentas, movimientos);

        return ResponseEntity.ok(reporte);
    }

}
