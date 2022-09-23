package com.nexos.sistema_inventario_nexos.ports.inputs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotNull
    @JsonProperty("nombre")
    private String nombre;

    @NotNull
    @JsonProperty("edad")
    private int edad;

    @NotNull
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    @JsonProperty("fecha_ingreso")
    private Date fechaIngreso;

}
