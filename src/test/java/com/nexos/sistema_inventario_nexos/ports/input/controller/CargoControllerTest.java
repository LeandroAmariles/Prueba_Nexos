package com.nexos.sistema_inventario_nexos.ports.input.controller;

import com.nexos.sistema_inventario_nexos.config.exception.handler.GlobalExceptionHandler;
import com.nexos.sistema_inventario_nexos.config.util.JsonUtils;
import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import com.nexos.sistema_inventario_nexos.core.model.Producto;
import com.nexos.sistema_inventario_nexos.core.model.Usuario;
import com.nexos.sistema_inventario_nexos.core.usecase.CargoService;
import com.nexos.sistema_inventario_nexos.core.usecase.ProductoService;
import com.nexos.sistema_inventario_nexos.core.usecase.UsuarioService;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ApiConstants;
import com.nexos.sistema_inventario_nexos.ports.inputs.controller.CargoController;
import com.nexos.sistema_inventario_nexos.ports.inputs.controller.ProductoController;
import com.nexos.sistema_inventario_nexos.ports.inputs.controller.UsuarioController;
import com.nexos.sistema_inventario_nexos.ports.inputs.mapper.CargoMapper;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateCargoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UsuarioRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.CargoResponse;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.UsuarioResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CargoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    CargoController controller;

    @Mock
    CargoService cargoService;

    @Spy
    CargoMapper mapper = Mappers.getMapper(CargoMapper.class);


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void CreateCargo_shouldReturn201() throws Exception {

        CreateCargoRequest request1 = CreateCargoRequest.builder()
                .nombre("Test Admin")
                .build();

        given(cargoService.createCargo(any(Cargo.class))).willReturn(99L);


        final String content1 = mockMvc.perform(post(ApiConstants.CARGO_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.objectToJson(request1)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);


        assertThat(content1).isEqualTo("http://localhost/cargo/99");


    }

    @Test
    void deleteCargo_shouldReturn204() throws Exception {
        mockMvc.perform(delete(ApiConstants.CARGO_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
