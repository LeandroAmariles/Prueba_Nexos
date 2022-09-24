package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

public interface ApiConstants {

    String USUARIOS_URI = "/usuarios";
    String PRODUCTOS_URI ="/productos";
    String CARGO_URI="/cargo";

    int DEFAULT_PAGE =0;
    int DEFAULT_SIZE=10;

    Function<Integer, String> uriByPageAsString = (page) ->
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .replaceQueryParam("page",page).toUriString();
}
