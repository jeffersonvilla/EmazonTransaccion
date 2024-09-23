package com.jeffersonvilla.emazon.transaccion.dominio.api.servicio;

import com.jeffersonvilla.emazon.transaccion.dominio.excepciones.CantidadSuministroException;
import com.jeffersonvilla.emazon.transaccion.dominio.excepciones.IdUsuarioNoEncontradoEnJwt;
import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.IStockApiPort;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.ISuministroPersistenciaPort;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.IUsuarioJwtPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.CANTIDAD_SUMINISTRO_MENOR_A_MINIMO;
import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.ID_USUARIO_NULL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SuministroCasoUsoTest {

    @Mock
    private IStockApiPort stockApi;

    @Mock
    private IUsuarioJwtPort usuarioJwt;

    @Mock
    private ISuministroPersistenciaPort persistencia;

    @InjectMocks
    private SuministroCasoUso suministroService;

    @Test
    void agregarSuministroDeberiaAgregarSuministroCorrectamente() {

        Long idUsuario = 1L;
        Integer cantidad = 10;

        Suministro suministro = new Suministro.SuministroBuilder()
                .setCantidad(cantidad)
                .build();

        Suministro suministroGuardado = new Suministro.SuministroBuilder()
                .setId(idUsuario)
                .setCantidad(cantidad)
                .build();

        when(usuarioJwt.extraerIdUsuario()).thenReturn(idUsuario);
        when(persistencia.guardarSuministro(suministro)).thenReturn(suministroGuardado);


        Suministro resultado = suministroService.agregarSuministro(suministro);

        assertEquals(idUsuario, suministro.getIdUsuario());
        assertEquals(suministroGuardado, resultado);

        verify(stockApi).agregarSuministro(suministro);
        verify(usuarioJwt).extraerIdUsuario();
        verify(persistencia).guardarSuministro(suministro);
    }

    @Test
    void agregarSuministroDeberiaLanzarExcepcionSiIdUsuarioEsNulo() {

        Integer cantidad = 10;

        Suministro suministro = new Suministro.SuministroBuilder()
                .setCantidad(cantidad)
                .build();

        when(usuarioJwt.extraerIdUsuario()).thenReturn(null);

        Exception exception = assertThrows(IdUsuarioNoEncontradoEnJwt.class, () -> {
            suministroService.agregarSuministro(suministro);
        });

        assertEquals(ID_USUARIO_NULL, exception.getMessage());
    }

    @Test
    void agregarSuministroDeberiaValidarCantidadSuministro() {

        Integer cantidad = -5;

        Suministro suministro =  new Suministro.SuministroBuilder()
                .setCantidad(cantidad)
                .build();


        Exception exception = assertThrows(CantidadSuministroException.class, () -> {
            suministroService.agregarSuministro(suministro);
        });

        assertEquals(CANTIDAD_SUMINISTRO_MENOR_A_MINIMO, exception.getMessage());
    }

}
