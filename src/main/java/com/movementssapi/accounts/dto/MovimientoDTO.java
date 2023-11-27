package com.movementssapi.accounts.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTO {
    private Integer idCuenta;
    private LocalDate fecha;
    private String tipoMovimiento;
    private BigDecimal saldo;
    private BigDecimal valor;
}
