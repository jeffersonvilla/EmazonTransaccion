package com.jeffersonvilla.emazon.transaccion.infraestructura.feign.excepciones;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.excepciones.RespuestaError;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.ERROR_LEEYENDO_RESPUESTA;
import static com.jeffersonvilla.emazon.transaccion.dominio.util.MensajesError.MENSAJE_NO_DISPONIBLE;

public class ErrorDecoderFeign implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        String mensajeError = obtenerMensajeError(response);
        switch (status) {
            case BAD_REQUEST -> {
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, mensajeError);
            }
            case NOT_FOUND -> {
                return new ResponseStatusException(HttpStatus.NOT_FOUND, mensajeError);
            }
            case INTERNAL_SERVER_ERROR -> {
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, mensajeError);
            }
            default -> {
                return defaultErrorDecoder.decode(methodKey, response);
            }
        }
    }

    private String obtenerMensajeError(Response response) {
        try {
            if (response.body() != null) {

                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                RespuestaError mensajeError = mapper.readValue(
                        response.body().asInputStream(), RespuestaError.class);

                return mensajeError.message();
            }

        } catch (IOException e) {
            return ERROR_LEEYENDO_RESPUESTA;
        }
        return MENSAJE_NO_DISPONIBLE;
    }
}