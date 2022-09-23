package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UpdateProductRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ProductoApi {

    ResponseEntity<ProductoResponse> createProducto(@Valid @RequestBody CreateProductoRequest request, @PathVariable Long id_usuario);

    ResponseEntity<ProductoResponse> updateProduct(@Valid @RequestBody UpdateProductRequest request, @NotNull @PathVariable Long id);

    void borrarProducto(@NotNull @PathVariable Long id);
}
