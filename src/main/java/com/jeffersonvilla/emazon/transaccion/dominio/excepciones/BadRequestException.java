package com.jeffersonvilla.emazon.transaccion.dominio.excepciones;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
