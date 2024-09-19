package com.jeffersonvilla.emazon.transaccion.infraestructura.feign.cliente;

import com.jeffersonvilla.emazon.transaccion.configuracion.FeignClientConfig;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.dto.AgregarSuministroRequestDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.dto.AgregarSuministroResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Stock", url = "${microservicio.stock.url-base}", configuration = FeignClientConfig.class)
public interface StockFeignClient {

    @PatchMapping(value = "/aumentar/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AgregarSuministroResponseDto agregarSuministro(@RequestBody AgregarSuministroRequestDto dto);
}
