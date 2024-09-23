package com.jeffersonvilla.emazon.transaccion.infraestructura.seguridad;

import com.jeffersonvilla.emazon.transaccion.dominio.spi.IUsuarioJwtPort;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.NO_SE_ENCUENTRAN_CREDENCIALES_USUARIO;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.ID_USUARIO_ATRIBUTO;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.TOKEN_JWT_ATRIBUTO;

@Component
@Primary
public class UsuarioJwtAdapter implements IUsuarioJwtPort {

    @Override
    public Long extraerIdUsuario() {
        Map<String, Object> details = obtenerDetalles();
        return Long.parseLong(details.get(ID_USUARIO_ATRIBUTO).toString());
    }

    @Override
    public String obtenerTokenJwt(){
        Map<String, Object> details = obtenerDetalles();
        return (String) details.get(TOKEN_JWT_ATRIBUTO);
    }

    private Map<String, Object> obtenerDetalles(){
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();

        if (autenticacion == null || !(autenticacion.getDetails() instanceof Map)) {
            throw new AuthenticationCredentialsNotFoundException(NO_SE_ENCUENTRAN_CREDENCIALES_USUARIO);
        }
        return (Map<String, Object>) autenticacion.getDetails();
    }
}
