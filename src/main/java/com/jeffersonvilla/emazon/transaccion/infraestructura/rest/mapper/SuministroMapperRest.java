package com.jeffersonvilla.emazon.transaccion.infraestructura.rest.mapper;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroRequestDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuministroMapperRest {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Suministro.SuministroBuilder agregarSuministroRequestDtoToSuministroBuilder(AgregarSuministroRequestDto dto);

    default Suministro agregarSuministroRequestDtoToSuministro(AgregarSuministroRequestDto dto){
        return agregarSuministroRequestDtoToSuministroBuilder(dto).build();
    }

    AgregarSuministroResponseDto suministroToAgregarSuministroResponseDto(Suministro suministro);
}
