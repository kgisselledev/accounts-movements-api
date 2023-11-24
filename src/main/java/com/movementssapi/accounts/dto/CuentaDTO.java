package com.movementssapi.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long Id;
    private String numeroCuenta;
    private BigDecimal saldo;
    private List<MovimientoDTO> movimientos;


}