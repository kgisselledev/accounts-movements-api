package com.movementssapi.accounts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "auditoria_movimientos")
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    private BigDecimal monto;

    @Column(name = "saldo_actual")
    private BigDecimal saldoActual;


}