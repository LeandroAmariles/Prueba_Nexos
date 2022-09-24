package com.nexos.sistema_inventario_nexos.ports.inputs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductosResponseList {

    private List<ProductoResponse> content = null;

    private String nextUri;

    private String previousUri;

    private Integer totalPages;

    private Long totalElements;
}
