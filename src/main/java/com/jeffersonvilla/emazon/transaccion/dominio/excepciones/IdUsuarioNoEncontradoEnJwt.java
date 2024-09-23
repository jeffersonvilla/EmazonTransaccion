package com.jeffersonvilla.emazon.transaccion.dominio.excepciones;

public class IdUsuarioNoEncontradoEnJwt extends BadRequestException {
    public IdUsuarioNoEncontradoEnJwt(String message) {
        super(message);
    }
}
