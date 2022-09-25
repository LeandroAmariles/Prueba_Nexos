package com.nexos.sistema_inventario_nexos.ports.inputs.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {

    private Long id;

    private String nombre;

    private int cantidad;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    @JsonProperty("fecha_ingreso")
    private Date fechaIngreso;

    private UsuarioResponse user;
}

