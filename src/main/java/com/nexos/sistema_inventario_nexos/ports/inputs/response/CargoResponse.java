package com.nexos.sistema_inventario_nexos.ports.inputs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoResponse {

    private Long id;

    private String nombre;
}
