package com.movementssapi.accounts.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoResponse {
    private boolean exitoso;
    private String mensaje;
}
