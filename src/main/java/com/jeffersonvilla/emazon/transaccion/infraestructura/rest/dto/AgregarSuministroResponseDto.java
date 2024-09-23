package com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto;

import java.time.LocalDate;

public record AgregarSuministroResponseDto(
        Long id,
        Long idArticulo,
        Integer cantidad,
        Long idUsuario,
        LocalDate fechaCreacion
) {
}
