package com.jeffersonvilla.emazon.transaccion.dominio.spi;

public interface IUsuarioJwtPort {

    Long extraerIdUsuario();
    String obtenerTokenJwt();
}
