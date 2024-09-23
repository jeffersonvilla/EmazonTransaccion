package com.jeffersonvilla.emazon.transaccion.dominio.api.servicio;

import com.jeffersonvilla.emazon.transaccion.dominio.api.ISuministroServicePort;
import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.IStockApiPort;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.ISuministroPersistenciaPort;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.IUsuarioJwtPort;

import java.time.LocalDate;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.ValidadorSuministro.validarCantidadSuministro;
import static com.jeffersonvilla.emazon.transaccion.dominio.util.ValidadorSuministro.validarIdUsuarioNoNulo;

public class SuministroCasoUso implements ISuministroServicePort {

    private final IStockApiPort stockApi;
    private final ISuministroPersistenciaPort persistencia;
    private final IUsuarioJwtPort usuarioJwt;

    public SuministroCasoUso(IStockApiPort stockApi,
                             ISuministroPersistenciaPort persistencia,
                             IUsuarioJwtPort usuarioJwt) {
        this.stockApi = stockApi;
        this.persistencia = persistencia;
        this.usuarioJwt = usuarioJwt;
    }

    @Override
    public Suministro agregarSuministro(Suministro suministro) {

        validarCantidadSuministro(suministro);

        stockApi.agregarSuministro(suministro);

        Long idUsuario = usuarioJwt.extraerIdUsuario();
        validarIdUsuarioNoNulo(idUsuario);
        suministro.setIdUsuario(idUsuario);

        suministro.setFechaCreacion(LocalDate.now());

        return persistencia.guardarSuministro(suministro);
    }

}
