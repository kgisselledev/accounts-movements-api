package com.movementssapi.accounts.dto;

import com.movementssapi.accounts.model.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Cuenta cuenta;
    private ClienteDTO cliente;
}
