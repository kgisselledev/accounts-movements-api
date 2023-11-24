package com.movementssapi.accounts.controller;

import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.saveCuenta(cuenta);
    }

    @GetMapping("/{cuentaId}")
    public Cuenta getCuentaById(@PathVariable Long cuentaId) {
        return cuentaService.getCuentaById(cuentaId);
    }

    @GetMapping("/")
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @PutMapping("/{cuentaId}")
    public Cuenta updateCuenta(@PathVariable Long cuentaId, @RequestBody Cuenta cuentaDetails) {
        return cuentaService.updateCuenta(cuentaId, cuentaDetails);
    }

    @DeleteMapping("/{cuentaId}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long cuentaId) {
        cuentaService.deleteCuenta(cuentaId);
        return ResponseEntity.ok().build();
    }
}

