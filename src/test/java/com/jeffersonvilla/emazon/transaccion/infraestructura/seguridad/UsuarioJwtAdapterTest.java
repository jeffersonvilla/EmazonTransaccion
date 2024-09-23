package com.jeffersonvilla.emazon.transaccion.infraestructura.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.NO_SE_ENCUENTRAN_CREDENCIALES_USUARIO;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.ID_USUARIO_ATRIBUTO;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.TOKEN_JWT_ATRIBUTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UsuarioJwtAdapterTest {

    private UsuarioJwtAdapter usuarioJwtAdapter;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioJwtAdapter = new UsuarioJwtAdapter();
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void extraerIdUsuarioDeberiaRetornarIdUsuarioCorrecto() {

        Map<String, Object> detalles = new HashMap<>();
        detalles.put(ID_USUARIO_ATRIBUTO, 123L);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(detalles);

        Long idUsuario = usuarioJwtAdapter.extraerIdUsuario();

        assertEquals(123L, idUsuario);
    }

    @Test
    void obtenerTokenJwtDeberiaRetornarTokenJwtCorrecto() {

        Map<String, Object> detalles = new HashMap<>();
        detalles.put(TOKEN_JWT_ATRIBUTO, "token123");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(detalles);

        String tokenJwt = usuarioJwtAdapter.obtenerTokenJwt();

        assertEquals("token123", tokenJwt);
    }

    @Test
    void extraerIdUsuarioDeberiaLanzarExcepcionSiAutenticacionEsNull() {

        when(securityContext.getAuthentication()).thenReturn(null);

        Exception ex = assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
            usuarioJwtAdapter.extraerIdUsuario();
        });

        assertEquals(NO_SE_ENCUENTRAN_CREDENCIALES_USUARIO, ex.getMessage());
    }

    @Test
    void extraerIdUsuarioDeberiaLanzarExcepcionSiDetallesNoSonUnMapa() {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn("detallesInvalidos");

        Exception ex = assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
            usuarioJwtAdapter.extraerIdUsuario();
        });

        assertEquals(NO_SE_ENCUENTRAN_CREDENCIALES_USUARIO, ex.getMessage());
    }
}