package com.movementssapi.accounts.controller;

import com.movementssapi.accounts.dto.MovimientoDTO;
import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.response.SaldoInsResponse;
import com.movementssapi.accounts.response.SaldoInsuficienteException;
import com.movementssapi.accounts.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<?> crearMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        try {
            Movimiento nuevoMovimiento = movimientoService.realizarMovimiento(
                                movimientoDTO.getIdCuenta(),
                                movimientoDTO.getValor(),
                                movimientoDTO.getTipoMovimiento(),
                                movimientoDTO,
                                movimientoDTO.getFecha()
                        );
            Movimiento movimientoPersistido = movimientoService.crearMovimiento(nuevoMovimiento);
            return new ResponseEntity<>(movimientoPersistido, HttpStatus.CREATED);

        } catch (SaldoInsuficienteException e) {
            SaldoInsResponse saldoInsResponse = new SaldoInsResponse("Saldo no disponible en la cuenta");
            return new ResponseEntity<>(saldoInsResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Integer id) {
        Optional<Movimiento> movimiento = movimientoService.obtenerMovimientoPorId(id);
        return movimiento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Integer id, @RequestBody Movimiento movimiento) {
        Optional<Movimiento> movimientoExistente = movimientoService.obtenerMovimientoPorId(id);

        if (movimientoExistente.isPresent()) {
            movimiento.setId(id);
            Movimiento movimientoActualizado = movimientoService.updateMovimiento(movimiento);
            return new ResponseEntity<>(movimientoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Integer id) {
        if (movimientoService.existeMovimientoPorId(id)) {
            movimientoService.deleteMovimiento(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
