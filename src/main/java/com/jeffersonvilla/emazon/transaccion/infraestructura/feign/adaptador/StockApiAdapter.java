package com.jeffersonvilla.emazon.transaccion.infraestructura.feign.adaptador;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.IStockApiPort;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.cliente.StockFeignClient;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.mapper.SuministroMapperFeign;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockApiAdapter implements IStockApiPort {

    private final StockFeignClient clienteFeign;
    private final SuministroMapperFeign mapper;

    @Override
    public void agregarSuministro(Suministro suministro) {
        clienteFeign.agregarSuministro(mapper.suministroToAgregarSuministroRequestDto(suministro));
    }
}
