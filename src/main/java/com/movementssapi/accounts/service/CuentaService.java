package com.movementssapi.accounts.service;

import com.movementssapi.accounts.dto.ReporteEstCuentaDTO;
import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.model.Movimiento;

import java.util.List;

public interface CuentaService {
    Cuenta saveCuenta(Cuenta cuentaSave);

    Cuenta getCuentaById(Long Id);

    List<Cuenta> getAllCuentas();

    Cuenta updateCuenta(Long Id, Cuenta cuentaDetails);

    void deleteCuenta(Long Id);

    void actualizarCuenta(Cuenta cuenta);

    ReporteEstCuentaDTO construirReporte(List<Cuenta> cuentas, List<Movimiento> movimientos);

    List<Cuenta> getCuentasPorCliente(Long Id);
}
