package com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.repositorio;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.ISuministroPersistenciaPort;
import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.mapper.SuministroMapperJpa;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuministroPersistenciaJpa implements ISuministroPersistenciaPort {

    private final SumnistroRepository sumnistroRepository;
    private final SuministroMapperJpa mapper;

    @Override
    public Suministro guardarSuministro(Suministro suministro) {
        return mapper.suministroEntityToSumnistro(
                sumnistroRepository.save(
                        mapper.suministroToSuministroEntity(suministro)
                )
        );
    }
}
