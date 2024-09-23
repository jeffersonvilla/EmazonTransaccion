package com.jeffersonvilla.emazon.transaccion.infraestructura.rest.controller;

import com.jeffersonvilla.emazon.transaccion.dominio.api.ISuministroServicePort;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroRequestDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroResponseDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.mapper.SuministroMapperRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Suministro API", description = "Operaciones relacionadas con los suministros")
@RequiredArgsConstructor
@RestController
@RequestMapping("suministro")
public class SuministroController {

    private final ISuministroServicePort suministroApi;
    private final SuministroMapperRest mapper;

    @Operation(summary = "Agregar suministro",
            description = "Crea nuevas existencias del artículo con los datos suministrados en el body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suministro agregado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgregarSuministroResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("agregar")
    public ResponseEntity<AgregarSuministroResponseDto> agregarSuministro(
            @Valid
            @RequestBody
            AgregarSuministroRequestDto suministroDto
    ){
        return new ResponseEntity<>(
                mapper.suministroToAgregarSuministroResponseDto(
                        suministroApi.agregarSuministro(
                                mapper.agregarSuministroRequestDtoToSuministro(suministroDto)
                        )
                ),
                HttpStatus.OK
        );
    }
}
