package com.jeffersonvilla.emazon.transaccion.infraestructura;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.Constantes.CLASE_UTILITARIA;

public class Constantes {

    private Constantes(){
        throw new IllegalStateException(CLASE_UTILITARIA);
    }

    public static final String RUTA_AGREGAR_SUMINISTRO = "/suministro/agregar";

    public static final String ID_USUARIO_ATRIBUTO = "id_usuario";
    public static final String TOKEN_JWT_ATRIBUTO = "token_jwt";

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final Integer TAMANO_HEADER = 7;

    public static final String JWT_TOKEN_EXPIRADO = "El token jwt ha expirado";
    public static final String JWT_TOKEN_NO_VALIDO = "El token jwt no es válido";

    public static final String ROL_USUARIO_CLAIM = "rol";
    public static final String ID_USUARIO_CLAIM = "id_usuario";

    public static final String FORMATO_CREACION_BEARER_TOKEN = "%s%s";
}
