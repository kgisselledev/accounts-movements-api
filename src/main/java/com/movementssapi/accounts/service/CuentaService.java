package com.movementssapi.accounts.service;


import com.movementssapi.accounts.dto.ResponseDTO;
import com.movementssapi.accounts.model.Cuenta;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CuentaService {
    Cuenta crearCuenta(Cuenta cuentaSave);

    boolean existeCuentaPorId(Integer id);

    Optional<Cuenta> obtenerCuentaPorId(Integer id);

    List<Cuenta> getAllCuentas();

    Optional<Cuenta> obtenerCuentaPorNumero(String numeroCuenta);

    Cuenta updateCuenta(Cuenta cuenta);

    void eliminarCuenta(Integer id);

    List<Cuenta> getCuentasPorCliente(Integer clienteId);

    ResponseDTO getClientebyId(Integer id);

}
