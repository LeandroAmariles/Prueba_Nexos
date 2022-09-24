package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UpdateProductRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductoResponse;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductosResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ProductoApi {

    ResponseEntity<ProductoResponse> createProducto(@Valid @RequestBody CreateProductoRequest request, @PathVariable Long id_usuario);

    ResponseEntity<ProductoResponse> updateProduct(@Valid @RequestBody UpdateProductRequest request, @NotNull @PathVariable Long id);

    void borrarProducto(@NotNull @PathVariable Long id);

    ResponseEntity<ProductosResponseList> getProductoList(@RequestParam Optional<Integer> page,
                                                          @RequestParam Optional<Integer> size);


    ResponseEntity<ProductosResponseList> getProductoListNameFilter(@RequestParam Optional<Integer> page,
                                                                    @RequestParam Optional<Integer> size,
                                                                    @PathVariable String nombre_producto);

    ResponseEntity<ProductosResponseList> getProductoListUsuarioFilter(@RequestParam Optional<Integer> page,
                                                                       @RequestParam Optional<Integer> size,
                                                                       @PathVariable String nombre_usuario);
}
