package com.movementssapi.accounts.service;

import com.movementssapi.accounts.dto.CuentaDTO;
import com.movementssapi.accounts.dto.MovimientoDTO;
import com.movementssapi.accounts.dto.ReporteEstCuentaDTO;
import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.model.Movimiento;
import com.movementssapi.accounts.repository.CuentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    private static final Logger logger = LoggerFactory.getLogger(CuentaServiceImpl.class);


    @Override
    public Cuenta saveCuenta(Cuenta cuentaSave) {
        Cuenta cuentasa = new Cuenta();
        cuentasa.setId(cuentaSave.getId());
        cuentasa.setNombre_cliente(cuentaSave.getNombre_cliente());
        cuentasa.setTipoCuenta(cuentaSave.getTipoCuenta());
        cuentasa.setNumeroCuenta(cuentaSave.getNumeroCuenta());
        cuentasa.setEstado(cuentaSave.getEstado());

        logger.info("Creando cuenta: ", cuentaSave);
        // Puedes realizar validaciones u operaciones adicionales antes de guardar
        return cuentaRepository.save(cuentaSave);
    }

    @Override
    public Cuenta getCuentaById(Long Id) {
        logger.info("Buscando cuenta con ID: {}", Id);
        return cuentaRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada con ID: " + Id));
    }

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta updateCuenta(Long Id, Cuenta cuentaDetails) {
        Cuenta cuenta = getCuentaById(Id);

        // Actualiza los campos necesarios
        cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.getEstado());

        // Puedes realizar más validaciones u operaciones antes de guardar la actualización

        return cuentaRepository.save(cuenta);
    }

    public void actualizarCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    @Override
    public ReporteEstCuentaDTO construirReporte(List<Cuenta> cuentas, List<Movimiento> movimientos) {
        ReporteEstCuentaDTO reporte = new ReporteEstCuentaDTO();
        List<CuentaDTO> cuentasDTO = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            CuentaDTO cuentaDTO = new CuentaDTO();
            cuentaDTO.setId(cuenta.getId());
            cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDTO.setSaldo(cuenta.getSaldoInicial());

            // Filtrar los movimientos correspondientes a esta cuenta
            List<Movimiento> movimientosCuenta = movimientos.stream()
                    .filter(m -> m.getCuenta().getId().equals(cuenta.getId()))
                    .collect(Collectors.toList());

            List<MovimientoDTO> movimientosDTO = new ArrayList<>();

            for (Movimiento movimiento : movimientosCuenta) {

                MovimientoDTO movimientoDTO = new MovimientoDTO();
                movimientoDTO.setFecha(movimiento.getFecha());
                movimientoDTO.setTipoMovimiento(String.valueOf(movimiento.getTipoMovimiento()));
                movimientoDTO.setValor(movimiento.getValor());
                movimientoDTO.setSaldo(movimiento.getSaldo());

                movimientosDTO.add(movimientoDTO);
            }

            cuentaDTO.setMovimientos(movimientosDTO);
            cuentasDTO.add(cuentaDTO);
        }

        reporte.setCuentas(cuentasDTO);
        return reporte;
    }

    @Override
    public List<Cuenta> getCuentasPorCliente(Long Id) {
        return cuentaRepository.findByClienteId(Id);
    }

    @Override
    public void deleteCuenta(Long Id) {
        Cuenta cuenta = getCuentaById(Id);
        cuentaRepository.delete(cuenta);
    }
}

