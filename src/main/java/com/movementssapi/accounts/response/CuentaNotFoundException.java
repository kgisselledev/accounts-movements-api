package com.movementssapi.accounts.response;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
