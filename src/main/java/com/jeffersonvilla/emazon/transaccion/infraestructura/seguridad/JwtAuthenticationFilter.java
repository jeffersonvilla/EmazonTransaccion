package com.jeffersonvilla.emazon.transaccion.infraestructura.seguridad;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.excepciones.RespuestaError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.AUTHORIZATION;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.BEARER;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.ID_USUARIO_ATRIBUTO;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.JWT_TOKEN_EXPIRADO;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.JWT_TOKEN_NO_VALIDO;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.TAMANO_HEADER;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.TOKEN_JWT_ATRIBUTO;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(TAMANO_HEADER);

        try {

            String username = jwtService.extraerUsername(jwt);
            String rol = jwtService.extraerRol(jwt);
            Integer idUsuario = jwtService.extraerIdUsuario(jwt);

            if (
                    username != null && rol != null &&
                            SecurityContextHolder.getContext().getAuthentication() == null &&
                            jwtService.tokenValido(jwt)
            ) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username, null,
                                List.of(new SimpleGrantedAuthority(rol)));

                Map<String, Object> details = new HashMap<>();
                details.put(ID_USUARIO_ATRIBUTO, idUsuario);
                details.put(TOKEN_JWT_ATRIBUTO, jwt);

                authToken.setDetails(details);

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            enviarRespuestaError(response, HttpStatus.UNAUTHORIZED.value(), JWT_TOKEN_EXPIRADO);
        } catch (SignatureException | MalformedJwtException | IllegalArgumentException e) {
            enviarRespuestaError(response, HttpStatus.UNAUTHORIZED.value(), JWT_TOKEN_NO_VALIDO);
        }
    }

    private void enviarRespuestaError(
            HttpServletResponse response, int status, String message
    ) throws IOException {

        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.getWriter().write(new ObjectMapper().writeValueAsString(
                new RespuestaError(HttpStatus.UNAUTHORIZED.toString(), message)));
        response.getWriter().flush();
    }

}
