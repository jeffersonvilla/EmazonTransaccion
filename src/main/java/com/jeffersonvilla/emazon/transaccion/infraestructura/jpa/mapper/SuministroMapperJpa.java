package com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.mapper;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.entidad.SuministroEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuministroMapperJpa {

    SuministroEntity suministroToSuministroEntity(Suministro suministro);

    Suministro.SuministroBuilder suministroEntityToSuministroBuilder(SuministroEntity entity);

    default Suministro suministroEntityToSumnistro(SuministroEntity entity){
        return suministroEntityToSuministroBuilder(entity).build();
    }
}
