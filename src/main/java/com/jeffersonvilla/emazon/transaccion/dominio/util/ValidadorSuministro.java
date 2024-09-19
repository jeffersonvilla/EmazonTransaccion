package com.jeffersonvilla.emazon.transaccion.dominio.util;

import com.jeffersonvilla.emazon.transaccion.dominio.excepciones.CantidadSuministroException;
import com.jeffersonvilla.emazon.transaccion.dominio.excepciones.IdUsuarioNoEncontradoEnJwt;
import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.Constantes.CANTIDAD_MINIMA_SUMINISTRO;
import static com.jeffersonvilla.emazon.transaccion.dominio.util.Constantes.CLASE_UTILITARIA;
import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.ID_USUARIO_NULL;

public class ValidadorSuministro {

    private ValidadorSuministro() {
        throw new IllegalStateException(CLASE_UTILITARIA);
    }

    public static void validarCantidadSuministro(Suministro suministro){

        if(suministro.getCantidad() < CANTIDAD_MINIMA_SUMINISTRO){
            throw new CantidadSuministroException(MensajesError.CANTIDAD_SUMINISTRO_MENOR_A_MINIMO);
        }

    }

    public static void validarIdUsuarioNoNulo(Long idUsuario){

        if(idUsuario == null){
            throw new IdUsuarioNoEncontradoEnJwt(ID_USUARIO_NULL);
        }
    }
}
