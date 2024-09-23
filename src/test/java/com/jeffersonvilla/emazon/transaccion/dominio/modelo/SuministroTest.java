package com.jeffersonvilla.emazon.transaccion.dominio.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SuministroTest {

    @Test
    void suministroBuilderDeberiaCrearSuministroCorrectamente() {
        Long id = 1L;
        Long idArticulo = 101L;
        Integer cantidad = 50;
        Long idUsuario = 1001L;
        LocalDate fechaCreacion = LocalDate.of(2024, 9, 22);

        Suministro suministro = new Suministro.SuministroBuilder()
                .setId(id)
                .setIdArticulo(idArticulo)
                .setCantidad(cantidad)
                .setIdUsuario(idUsuario)
                .setFechaCreacion(fechaCreacion)
                .build();

        assertEquals(id, suministro.getId());
        assertEquals(idArticulo, suministro.getIdArticulo());
        assertEquals(cantidad, suministro.getCantidad());
        assertEquals(idUsuario, suministro.getIdUsuario());
        assertEquals(fechaCreacion, suministro.getFechaCreacion());
    }

    @Test
    void suministroSetIdUsuarioDeberiaActualizarElIdUsuario() {
        Suministro suministro = new Suministro.SuministroBuilder().build();

        Long nuevoIdUsuario = 2002L;
        suministro.setIdUsuario(nuevoIdUsuario);

        assertEquals(nuevoIdUsuario, suministro.getIdUsuario());
    }

    @Test
    void suministroSetFechaCreacionDeberiaActualizarFechaCreacion() {
        Suministro suministro = new Suministro.SuministroBuilder().build();

        LocalDate nuevaFechaCreacion = LocalDate.of(2024, 9, 22);
        suministro.setFechaCreacion(nuevaFechaCreacion);

        assertEquals(nuevaFechaCreacion, suministro.getFechaCreacion());
    }

    @Test
    void suministroBuilderDeberiaCrearSuministroConValoresNulosSiNoSeDefinen() {
        Suministro suministro = new Suministro.SuministroBuilder().build();
        
        assertNull(suministro.getId());
        assertNull(suministro.getIdArticulo());
        assertNull(suministro.getCantidad());
        assertNull(suministro.getIdUsuario());
        assertNull(suministro.getFechaCreacion());
    }
}