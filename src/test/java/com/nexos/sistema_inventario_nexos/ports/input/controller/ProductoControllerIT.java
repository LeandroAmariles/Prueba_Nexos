package com.nexos.sistema_inventario_nexos.ports.input.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nexos.sistema_inventario_nexos.H2Config;
import com.nexos.sistema_inventario_nexos.config.util.JsonUtils;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ApiConstants;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UpdateProductRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductoResponse;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductosResponseList;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductoControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    @JsonIgnoreProperties(ignoreUnknown = true)
    void createProduct_shouldReturn201() throws Exception {

        CreateProductoRequest request = CreateProductoRequest.builder()
                .nombre("Test P5")
                .fechaIngreso(Calendar.getInstance().getTime())
                .cantidad(5)
                .build();

        String content = mockMvc.perform(post(ApiConstants.PRODUCTOS_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated()).andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductoResponse response = JsonUtils.jsonToObject(content, ProductoResponse.class);

        assertThat(response).isInstanceOf(ProductoResponse.class);

    }

    @Test
    @Order(2)
    void getProduct_shouldReturn200() throws Exception {

        String content = mockMvc.perform(get(ApiConstants.PRODUCTOS_URI))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        ProductosResponseList response = JsonUtils.jsonToObject(content, ProductosResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/productos?page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/productos?page=0");
        assertThat(response.getContent()).isNotNull();

    }

    @Test
    @Order(3)
    void updateProduct_shouldReturn204() throws Exception {

        UpdateProductRequest request = UpdateProductRequest.builder()
                .cantidad(55)
                .build();

        mockMvc.perform(patch(ApiConstants.PRODUCTOS_URI + "/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @Order(4)
    void deleteProduct_shouldReturn204() throws Exception {
        mockMvc.perform(delete(ApiConstants.PRODUCTOS_URI+"/1/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
