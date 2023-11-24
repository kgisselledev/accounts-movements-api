package com.movementssapi.accounts.service;

import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.repository.MovimientoRepository;
import com.movementssapi.accounts.response.MovimientoResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service

public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    @Override
    @Transactional
    public MovimientoResponse saveMovimiento(Movimiento movimiento) {
        MovimientoResponse movimientoResponse = new MovimientoResponse();

        BigDecimal saldoActual = movimiento.getCuenta().getSaldoInicial();
        BigDecimal valorMovimiento = movimiento.getValor();

        if (movimiento.getTipoMovimiento() == Movimiento.TipoMovimiento.RETIRO && saldoActual.compareTo(valorMovimiento) < 0) {
            // Si es un retiro y el saldo no es suficiente, configurar el resultado con un mensaje de error
            movimientoResponse.setExitoso(false);
            movimientoResponse.setMensaje("Saldo no disponible para realizar el retiro.");
        } else {
            // Actualizar el saldo de la cuenta
            BigDecimal nuevoSaldo = calcularNuevoSaldo(movimiento.getCuenta().getSaldoInicial(), movimiento.getTipoMovimiento(), movimiento.getValor());
            movimiento.getCuenta().setSaldoInicial(nuevoSaldo);

            // Guardar el movimiento y actualizar la cuenta
            movimiento = movimientoRepository.save(movimiento);
            cuentaService.actualizarCuenta(movimiento.getCuenta());

            movimientoResponse.setExitoso(true);
            movimientoResponse.setMensaje("Movimiento registrado exitosamente.");

        }
        return movimientoResponse;
    }

    @Transactional
    private BigDecimal calcularNuevoSaldo(BigDecimal saldoActual, Movimiento.TipoMovimiento tipoMovimiento, BigDecimal valor) {
        if (tipoMovimiento == Movimiento.TipoMovimiento.DEPOSITO) {
            return saldoActual.add(valor);
        } else if (tipoMovimiento == Movimiento.TipoMovimiento.RETIRO) {
            return saldoActual.subtract(valor);
        }
        // Agrega más lógica según sea necesario para otros tipos de movimiento
        return saldoActual;
    }



    @Override
    public Movimiento getMovimientoById(Long Id) {
        return movimientoRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado con ID: " + Id));
    }

    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento updateMovimiento(Long Id, Movimiento movimientoDetails) {
        Movimiento movimiento = getMovimientoById(Id);

        // Actualiza los campos necesarios
        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());

        // Puedes realizar más validaciones u operaciones antes de guardar la actualización

        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> getMovimientosPorClienteYFecha(Long Id, Date fechaInicio, Date fechaFin) {
        // Implementa la lógica para obtener movimientos por cliente y dentro del rango de fechas
        // Puedes utilizar el repositorio de movimientos para realizar la consulta
        // Ten en cuenta que esto dependerá de la estructura de tu base de datos y cómo has modelado las relaciones
        // Entre las entidades Cliente, Cuenta y Movimiento
        // Ejemplo:
        return movimientoRepository.findByCuentaClienteIdAndFechaBetween(Id, fechaInicio, fechaFin);
    }

    @Override
    public void deleteMovimiento(Long Id) {
        Movimiento movimiento = getMovimientoById(Id);
        movimientoRepository.delete(movimiento);
    }
}
