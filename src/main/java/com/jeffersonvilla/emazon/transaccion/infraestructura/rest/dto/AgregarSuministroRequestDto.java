package com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.Constantes.CANTIDAD_MINIMA_SUMINISTRO;

public record AgregarSuministroRequestDto(
        @NotNull Long idArticulo,
        @NotNull @Min(CANTIDAD_MINIMA_SUMINISTRO) Integer cantidad) {
}
