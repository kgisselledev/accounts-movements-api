package com.movementssapi.accounts.service;

import com.movementssapi.accounts.dto.MovimientoDTO;
import com.movementssapi.accounts.model.AuditoriaMovimiento;
import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.repository.MovimientoRepository;
import com.movementssapi.accounts.repository.auditoriaMovimientoRepository;
import com.movementssapi.accounts.response.CuentaNotFoundException;
import com.movementssapi.accounts.response.SaldoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private auditoriaMovimientoRepository auditoriaMovimientoRepository;
    @Autowired
    private CuentaService cuentaService;

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {

        return movimientoRepository.save(movimiento);
    }
    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public Optional<Movimiento> obtenerMovimientoPorId(Integer id) {
        return movimientoRepository.findById(id);
    }

    @Override
    public boolean existeMovimientoPorId(Integer id) {
        return movimientoRepository.existsById(id);
    }
    @Override
    public Movimiento updateMovimiento(Movimiento movimiento) {
            return movimientoRepository.save(movimiento);
    }
    @Override
    public void deleteMovimiento(Integer id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public Movimiento realizarMovimiento(Integer idCuenta, BigDecimal monto, String tipoMovimiento, MovimientoDTO movimientoRes, LocalDate fecha) {

        Cuenta cuenta = cuentaService.obtenerCuentaPorId(idCuenta)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con ID: " + idCuenta));

        BigDecimal valor = movimientoRes.getValor();
        BigDecimal saldoActual = cuenta.getSaldoActual();


        if ("Ahorro".equalsIgnoreCase(movimientoRes.getTipoMovimiento()) && saldoActual.compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente en la cuenta con ID: " + idCuenta);
        }

        if ("Corriente".equalsIgnoreCase(movimientoRes.getTipoMovimiento()) && saldoActual.compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente en la cuenta con ID: " + idCuenta);
        }

        if ("Ahorro".equalsIgnoreCase(movimientoRes.getTipoMovimiento())) {
            cuenta.setSaldoActual(saldoActual.subtract(valor));
        } else if ("Corriente".equalsIgnoreCase(movimientoRes.getTipoMovimiento())) {
            cuenta.setSaldoActual(saldoActual.add(valor));
        } else {
            throw new IllegalArgumentException("Tipo de movimiento no válido: " + movimientoRes.getTipoMovimiento());
        }

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(movimientoRes.getFecha());
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setValor(movimientoRes.getValor());
        movimiento.setSaldo(cuenta.getSaldoActual());

        AuditoriaMovimiento auditoria = new AuditoriaMovimiento();
        auditoria.setIdCuenta(idCuenta);
        auditoria.setFecha(movimientoRes.getFecha());
        auditoria.setTipoMovimiento(tipoMovimiento);
        auditoria.setMonto(movimientoRes.getValor());
        auditoria.setSaldoActual(cuenta.getSaldoActual());
        auditoriaMovimientoRepository.save(auditoria);

        movimientoRepository.save(movimiento);
        cuentaService.updateCuenta(cuenta);

        return movimiento;
    }
    @Override
    public List<Movimiento> obtenerMovimientosEnRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {

        List<Movimiento> movimientos = movimientoRepository.findByFechaBetween(fechaInicio, fechaFin);

        return movimientos;
    }

}


