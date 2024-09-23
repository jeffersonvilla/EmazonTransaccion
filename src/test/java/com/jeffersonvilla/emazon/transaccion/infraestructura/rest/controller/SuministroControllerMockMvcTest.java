package com.jeffersonvilla.emazon.transaccion.infraestructura.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffersonvilla.emazon.transaccion.dominio.api.ISuministroServicePort;
import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroRequestDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.dto.AgregarSuministroResponseDto;
import com.jeffersonvilla.emazon.transaccion.infraestructura.rest.mapper.SuministroMapperRest;
import com.jeffersonvilla.emazon.transaccion.infraestructura.seguridad.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static com.jeffersonvilla.emazon.transaccion.dominio.util.Constantes.ROL_AUX_BODEGA;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SuministroController.class)
@WithMockUser(username = "aux", authorities = {ROL_AUX_BODEGA})
class SuministroControllerMockMvcTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ISuministroServicePort suministroApi;

    @MockBean
    private SuministroMapperRest mapper;

    @MockBean
    private JwtService jwtService;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void agregarSuministroConExito() throws Exception {

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

        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/suministro/agregar")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.idUsuario").value(idUsuario))
                .andExpect(jsonPath("$.fechaCreacion").value(fechaCreacion.toString()))
                .andExpect(jsonPath("$.idArticulo").value(idArticulo))
                .andExpect(jsonPath("$.cantidad").value(cantidad));

    }
}
