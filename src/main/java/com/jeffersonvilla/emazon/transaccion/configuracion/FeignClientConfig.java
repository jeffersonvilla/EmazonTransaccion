package com.jeffersonvilla.emazon.transaccion.configuracion;

import com.jeffersonvilla.emazon.transaccion.dominio.spi.IUsuarioJwtPort;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.excepciones.ErrorDecoderFeign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.AUTHORIZATION;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.BEARER;
import static com.jeffersonvilla.emazon.transaccion.infraestructura.Constantes.FORMATO_CREACION_BEARER_TOKEN;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {

    private final IUsuarioJwtPort jwtPort;

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> requestTemplate.header(AUTHORIZATION,
                String.format(FORMATO_CREACION_BEARER_TOKEN, BEARER, jwtPort.obtenerTokenJwt()));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoderFeign();
    }
}
