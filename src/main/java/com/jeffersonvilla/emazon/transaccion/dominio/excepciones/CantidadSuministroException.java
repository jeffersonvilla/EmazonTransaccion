package com.jeffersonvilla.emazon.transaccion.dominio.excepciones;

public class CantidadSuministroException extends BadRequestException {
    public CantidadSuministroException(String message) {
        super(message);
    }
}
