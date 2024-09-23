package com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.repositorio;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.entidad.SuministroEntity;
import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.mapper.SuministroMapperJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SuministroPersistenciaJpaTest {

    @Mock
    private SumnistroRepository sumnistroRepository;

    @Mock
    private SuministroMapperJpa mapper;

    @InjectMocks
    private SuministroPersistenciaJpa suministroPersistenciaJpa;

    @Test
    void guardarSuministroDeberiaGuardarYRetornarSuministro() {

        Suministro suministro = new Suministro.SuministroBuilder()
                .setId(1L)
                .setIdArticulo(101L)
                .setCantidad(50)
                .setIdUsuario(1001L)
                .build();

        SuministroEntity suministroEntity = new SuministroEntity();

        when(mapper.suministroToSuministroEntity(suministro)).thenReturn(suministroEntity);
        when(sumnistroRepository.save(suministroEntity)).thenReturn(suministroEntity);
        when(mapper.suministroEntityToSumnistro(suministroEntity)).thenReturn(suministro);

        Suministro resultado = suministroPersistenciaJpa.guardarSuministro(suministro);

        assertEquals(suministro, resultado);

        verify(mapper).suministroToSuministroEntity(suministro);
        verify(sumnistroRepository).save(suministroEntity);
        verify(mapper).suministroEntityToSumnistro(suministroEntity);
    }
}