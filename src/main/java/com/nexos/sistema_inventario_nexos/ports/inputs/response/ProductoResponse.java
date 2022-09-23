package com.nexos.sistema_inventario_nexos.ports.inputs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {

    private Long id;

    private String nombre;

    private int cantidad;

    private Date fechaIngreso;

    private UsuarioResponse user;
}
