package com.nexos.sistema_inventario_nexos.ports.inputs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCargoRequest {

    @NotEmpty
    @JsonProperty("nombre")
    private String nombre;

}
