package com.jeffersonvilla.emazon.transaccion.infraestructura.feign.adaptador;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.cliente.StockFeignClient;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.dto.AgregarSuministroRequestDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.mapper.SuministroMapperFeign;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockApiAdapterTest {

    @Mock
    private StockFeignClient clienteFeign;

    @Mock
    private SuministroMapperFeign mapper;

    @InjectMocks
    private StockApiAdapter stockApiAdapter;

    @Test
    void agregarSuministroDeberiaInvocarClienteFeignConDtoCorrecto() {

        Long idArticulo = 101L;
        Integer cantidad = 50;

        Suministro suministro = new Suministro.SuministroBuilder()
                .setId(1L)
                .setIdArticulo(idArticulo)
                .setCantidad(cantidad)
                .setIdUsuario(1001L)
                .build();

        AgregarSuministroRequestDto requestDto = new AgregarSuministroRequestDto(idArticulo, cantidad);

        when(mapper.suministroToAgregarSuministroRequestDto(suministro)).thenReturn(requestDto);

        stockApiAdapter.agregarSuministro(suministro);

        verify(mapper).suministroToAgregarSuministroRequestDto(suministro);
        verify(clienteFeign).agregarSuministro(requestDto);
    }
}