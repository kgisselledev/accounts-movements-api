package com.movementssapi.accounts.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name ="cuentas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;
    @Column(name = "estado")
    private String estado;
    @Column(name = "saldo_actual")
    private BigDecimal saldoActual;

}