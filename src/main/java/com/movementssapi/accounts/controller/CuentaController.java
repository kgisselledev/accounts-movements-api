package com.movementssapi.accounts.controller;

import com.movementssapi.accounts.dto.ResponseDTO;
import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @GetMapping("/numero/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerCuentaPorNumero(@PathVariable String numeroCuenta) {
        Optional<Cuenta> cuenta = cuentaService.obtenerCuentaPorNumero(numeroCuenta);
        return cuenta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {

        List<Cuenta> cuentas = cuentaService.getAllCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseDTO getClientebyId(@PathVariable Integer id){
        return cuentaService.getClientebyId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta>  updateCuenta(@PathVariable Integer id, @RequestBody Cuenta cuenta) {
        Optional<Cuenta> cuentaExistente = cuentaService.obtenerCuentaPorId(id);

        if (cuentaExistente.isPresent()) {
            cuenta.setId(id);
            Cuenta cuentaActualizada = cuentaService.updateCuenta(cuenta);
            return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Integer id) {
        if (cuentaService.existeCuentaPorId(id)) {
            cuentaService.eliminarCuenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

