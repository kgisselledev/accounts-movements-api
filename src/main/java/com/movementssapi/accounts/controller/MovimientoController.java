package com.movementssapi.accounts.controller;

import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.response.MovimientoResponse;
import com.movementssapi.accounts.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoResponse> registrarMovimiento(@RequestBody Movimiento movimiento) {
        MovimientoResponse movimientoResponse = movimientoService.saveMovimiento(movimiento);

        if (movimientoResponse.isExitoso()) {
            return ResponseEntity.ok(movimientoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(movimientoResponse);
        }
    }

    @GetMapping("/{Id}")
    public Movimiento getMovimientoById(@PathVariable Long movimientoId) {
        return movimientoService.getMovimientoById(movimientoId);
    }

    @GetMapping("/movimientos")
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @PutMapping("/{Id}")
    public Movimiento updateMovimiento(@PathVariable Long movimientoId, @RequestBody Movimiento movimientoDetails) {
        return movimientoService.updateMovimiento(movimientoId, movimientoDetails);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteMovimiento(@PathVariable Long movimientoId) {
        movimientoService.deleteMovimiento(movimientoId);
        return ResponseEntity.ok().build();
    }
}
