package com.jeffersonvilla.emazon.transaccion.infraestructura.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.ID_USUARIO_CLAIM;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.ROL_USUARIO_CLAIM;

@Service
public class JwtService {

    @Value("${CLAVE_JWT}")
    private String claveSecreta;

    public String extraerUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key obtenerClaveSecreta(){
        byte[] keyBytes = Decoders.BASE64.decode(claveSecreta);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) obtenerClaveSecreta())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean tokenValido(String token) {
        return !tokenExpirado(token);
    }

    private Boolean tokenExpirado(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extraerRol(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(ROL_USUARIO_CLAIM, String.class);
    }

    public Integer extraerIdUsuario(String token){
        Claims claims = extractAllClaims(token);
        return claims.get(ID_USUARIO_CLAIM, Integer.class);
    }

}
