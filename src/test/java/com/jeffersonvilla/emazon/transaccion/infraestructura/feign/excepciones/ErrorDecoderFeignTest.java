package com.jeffersonvilla.emazon.transaccion.infraestructura.feign.excepciones;

import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.ERROR_LEEYENDO_RESPUESTA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ErrorDecoderFeignTest {

    private ErrorDecoderFeign errorDecoderFeign;

    @Mock
    private ErrorDecoder.Default defaultErrorDecoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        errorDecoderFeign = new ErrorDecoderFeign();
    }

    @Test
    void decodeDeberiaRetornarBadRequestException() {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .reason("Bad Request")
                .request(mock(Request.class))
                .build();

        Exception exception = errorDecoderFeign.decode("test", response);

        assertTrue(exception instanceof ResponseStatusException);
        assertEquals(HttpStatus.BAD_REQUEST, ((ResponseStatusException) exception).getStatusCode());
    }

    @Test
    void decodeDeberiaRetornarNotFoundException() {
        Response response = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reason("Not Found")
                .request(mock(Request.class))
                .build();

        Exception exception = errorDecoderFeign.decode("test", response);

        assertTrue(exception instanceof ResponseStatusException);
        assertEquals(HttpStatus.NOT_FOUND, ((ResponseStatusException) exception).getStatusCode());
    }

    @Test
    void decodeDeberiaRetornarInternalServerErrorException() {

        Response response = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reason("Internal Server Error")
                .request(mock(Request.class))
                .build();

        Exception exception = errorDecoderFeign.decode("test", response);

        assertTrue(exception instanceof ResponseStatusException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ((ResponseStatusException) exception).getStatusCode());
    }

    @Test
    void obtenerMensajeErrorDeberiaRetornarMensajeDelCuerpo() {

        String mensaje = "Error encontrado";
        String json = "{\"message\": \""+mensaje+"\"}";
        ByteArrayInputStream responseBody = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .reason("Bad Request")
                .body(responseBody, json.length())
                .request(mock(Request.class))
                .build();

        String mensajeError = errorDecoderFeign.decode("test", response).getMessage();

        assertEquals(HttpStatus.BAD_REQUEST.toString() +" \""+ mensaje+"\"", mensajeError);
    }

    @Test
    void obtenerMensajeErrorDeberiaRetornarErrorAlLeerRespuestaSiHayExcepcion() {

        ByteArrayInputStream responseBody = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));

        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .reason("Bad Request")
                .body(responseBody, 0)
                .request(mock(Request.class))
                .build();

        String mensajeError = errorDecoderFeign.decode("test", response).getMessage();

        assertEquals(HttpStatus.BAD_REQUEST.toString() +" \""+ ERROR_LEEYENDO_RESPUESTA+"\"", mensajeError);
    }
}