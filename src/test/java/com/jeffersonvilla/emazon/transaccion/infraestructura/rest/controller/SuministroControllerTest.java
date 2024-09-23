package com.jeffersonvilla.emazon.transaccion.infraestructura.rest.controller;

import com.jeffersonvilla.emazon.transaccion.dominio.api.ISuministroServicePort;
import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroRequestDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroResponseDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.mapper.SuministroMapperRest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SuministroControllerTest {

    @Mock
    private ISuministroServicePort suministroApi;

    @Mock
    private SuministroMapperRest mapper;

    @InjectMocks
    private SuministroController controller;

    @Test
    void agregarSuministroDeberiaRetornarResponseEntityConStatusOk() {

        Long id = 1L;
        Long idUsuario = 1L;
        LocalDate fechaCreacion = LocalDate.now();
        Long idArticulo = 1L;
        Integer cantidad = 10;

        AgregarSuministroRequestDto requestDto = new AgregarSuministroRequestDto(idArticulo, cantidad);
        Suministro suministro = new Suministro.SuministroBuilder().build();
        AgregarSuministroResponseDto responseDto =
                new AgregarSuministroResponseDto(id, idArticulo, cantidad, idUsuario, fechaCreacion);

        when(mapper.agregarSuministroRequestDtoToSuministro(requestDto)).thenReturn(suministro);
        when(suministroApi.agregarSuministro(suministro)).thenReturn(suministro);
        when(mapper.suministroToAgregarSuministroResponseDto(suministro)).thenReturn(responseDto);

        ResponseEntity<AgregarSuministroResponseDto> response = controller.agregarSuministro(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(mapper).agregarSuministroRequestDtoToSuministro(requestDto);
        verify(suministroApi).agregarSuministro(suministro);
        verify(mapper).suministroToAgregarSuministroResponseDto(suministro);

    }

}