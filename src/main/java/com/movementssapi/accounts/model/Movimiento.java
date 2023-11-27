package com.movementssapi.accounts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "movimientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "fecha")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;
    @Column(name = "tipo_movimiento")
    private String  tipoMovimiento;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "saldo")
    private BigDecimal saldo;

}
