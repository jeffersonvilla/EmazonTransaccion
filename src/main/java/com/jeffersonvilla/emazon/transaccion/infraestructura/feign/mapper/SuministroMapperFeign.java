package com.jeffersonvilla.emazon.transaccion.infraestructura.feign.mapper;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;

import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.dto.AgregarSuministroRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuministroMapperFeign {

    AgregarSuministroRequestDto suministroToAgregarSuministroRequestDto(Suministro suministro);
}
