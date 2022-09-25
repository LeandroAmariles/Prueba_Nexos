package com.nexos.sistema_inventario_nexos.ports.input.controller;

import com.nexos.sistema_inventario_nexos.config.exception.handler.GlobalExceptionHandler;

import com.nexos.sistema_inventario_nexos.config.util.JsonUtils;
import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import com.nexos.sistema_inventario_nexos.core.model.Usuario;
import com.nexos.sistema_inventario_nexos.core.usecase.CargoService;
import com.nexos.sistema_inventario_nexos.core.usecase.UsuarioService;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ApiConstants;
import com.nexos.sistema_inventario_nexos.ports.inputs.controller.UsuarioController;
import com.nexos.sistema_inventario_nexos.ports.inputs.mapper.CargoMapper;
import com.nexos.sistema_inventario_nexos.ports.inputs.mapper.UsuarioMapper;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateCargoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UsuarioRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.UsuarioResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    UsuarioController controller;

    @Mock
    UsuarioService usuarioService;

    @Spy
    UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void createUser_shouldReturn203() throws Exception{

        CreateCargoRequest cargo = CreateCargoRequest.builder()
                .nombre("Cargo 1").build();

        mockMvc.perform(post(ApiConstants.CARGO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(cargo)));

        UsuarioRequest request = UsuarioRequest.builder()
                .nombre("Test")
                .edad(25)
                .fechaIngreso(new Date())
                .build();

         mockMvc.perform(post(ApiConstants.USUARIOS_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                 .andDo(print());

    }
    @Test
    void deleteUser_shouldReturn204() throws Exception{
        mockMvc.perform(delete(ApiConstants.USUARIOS_URI+"/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
