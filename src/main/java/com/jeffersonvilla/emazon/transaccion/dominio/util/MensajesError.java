package com.jeffersonvilla.emazon.transaccion.dominio.util;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.Constantes.CLASE_UTILITARIA;

public class MensajesError {

    private MensajesError() {
        throw new IllegalStateException(CLASE_UTILITARIA);
    }

    public static final String CANTIDAD_SUMINISTRO_MENOR_A_MINIMO = "La cantidad del suministro debe ser mayor " +
            "o igual a " + Constantes.CANTIDAD_MINIMA_SUMINISTRO;

    public static final String ID_USUARIO_NULL = "El id de usuario es nulo";

    public static final String NO_SE_ENCUENTRAN_CREDENCIALES_USUARIO = "No se encontraron credenciales de " +
            "autenticación o los detalles no son válidos.";

    public static final String ERROR_CONECCION = "No se ha podido conectar con otro microservicio";

    public static final String ERROR_LEEYENDO_RESPUESTA = "Error leyendo respuesta de otro microservicio";

    public static final String MENSAJE_NO_DISPONIBLE = "Mensaje no disponible";
}
