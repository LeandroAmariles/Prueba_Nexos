package com.nexos.sistema_inventario_nexos.ports.inputs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("next_uri")
    private String nextUri;

    @JsonProperty("previous_uri")
    private String previousUri;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;
}
