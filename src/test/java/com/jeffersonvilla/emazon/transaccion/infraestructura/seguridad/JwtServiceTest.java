package com.jeffersonvilla.emazon.transaccion.infraestructura.seguridad;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    private final String claveSecreta = "a522a5d03de1e7364f4985067bba67f4a0d6e4e1ed482d07bb70b" +
            "94a39f1580a8ce82094888ff449bc23a9bfc6715dbdcd82347c94127536825f468d3e4a2186" +
            "c20ca826705d642bcf486e0cbebf1a7df0b68d37772c8ddce876d500ee895492289380ed404" +
            "9ccfc0a7f8dfe700ddb52e56b6c9d658e32a1e2134fed35870dc83ba4b47c3336153e779bab" +
            "f72cab925002fec514c31e14fa09d3471a8bb178fd06baef291ad65f7058a09b4499ba30ae9" +
            "23002d6c9b469b440970486eac0b0523ade7421e465fce4012bca6bd2b5580c52f3212e0ec6" +
            "4bc0f5c5afca71bf1e8f6040fd83c7fdbec33e615d29c6a52d40e3615cf3aa55c014b3a1c15" +
            "50f26008c";

    private final String tokenGenerado = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2wiOiJhZG1pbiIsInN1YiI6ImNvcnJlb0BwcnVlYmEuY29tIiwiaWRfdXN1YXJpbyI6MSwiZXhwIjoyODU5OTk0NzI1LCJpYXQiOjE1MTYyMzkwMjJ9.KVUAWXw4g-I2BqEnj635oJB2rY89Y4zS6EOq3lwSmbU";

    private final String correo = "correo@prueba.com";
    private final String rol = "admin";
    private final Integer idUsuario = 1;

    @BeforeEach
    void setUp(){
        ReflectionTestUtils.setField(jwtService, "claveSecreta", this.claveSecreta);
    }

    @Test
    void testExtraerUsername() {

        String username = jwtService.extraerUsername(tokenGenerado);
        assertEquals(correo, username);
    }

    @Test
    void testExtraerRol() {
        assertEquals(rol, jwtService.extraerRol(tokenGenerado));
    }

    @Test
    void testExtraerIdUsuario() {
        assertEquals(idUsuario, jwtService.extraerIdUsuario(tokenGenerado));
    }

    @Test
    void testTokenValido() {
        assertTrue(jwtService.tokenValido(tokenGenerado));
    }

    @Test
    void testTokenInvalidoPorExpiracion() {

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpZF91c3VhcmlvIjoxLCJyb2wiOiJhdXhfYm9kZWd" +
                "hIiwic3ViIjoidXN1YXJpb0BlamVtcGxvLmNvbSIsImlhdCI6MTcyNTU0ODY1MCwiZXhwIj" +
                "oxNzE1NjI1MDUwfQ.d57RIrJv6gjXj310gzK5FXrA7UQNj8X4h1sYApTjgkECEG-dJJM9Nl" +
                "rQ4xW8Ntrhu4GvpbSkmV_JxDtiBXJ5Cg";

        assertThrows(ExpiredJwtException.class,
                () -> jwtService.tokenValido(token));
    }

    @Test
    void testExtraerExpiration() {
        Date expiration = jwtService.extractExpiration(tokenGenerado);
        assertNotNull(expiration);
        assertTrue(expiration.after(new Date()));
    }
}