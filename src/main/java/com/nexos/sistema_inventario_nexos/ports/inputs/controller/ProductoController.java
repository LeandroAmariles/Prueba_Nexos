package com.nexos.sistema_inventario_nexos.ports.inputs.controller;

import com.nexos.sistema_inventario_nexos.core.model.Producto;
import com.nexos.sistema_inventario_nexos.core.usecase.ProductoService;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ProductoApi;
import com.nexos.sistema_inventario_nexos.ports.inputs.mapper.ProductoMapper;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UpdateProductRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController implements ProductoApi {

    private final ProductoMapper productoMapper;
    private final ProductoService productoService;


    @Override
    @PostMapping("/{id_usuario}")
    public ResponseEntity<ProductoResponse> createProducto(@Valid @RequestBody CreateProductoRequest request, @PathVariable Long id_usuario) {
        Producto producto = productoMapper.createProductRequestToEntity(request);
        productoService.crearNuevoProducto(producto, id_usuario);
        ProductoResponse productoR = productoMapper.entityToResponse(producto);
        return new ResponseEntity<>(productoR, HttpStatus.CREATED);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponse> updateProduct(UpdateProductRequest request, Long id) {
        Producto producto = productoMapper.updateProductToEntity(request);
        Producto productoR = productoService.actualizarProductoSiExiste(producto, id);

        return new ResponseEntity<>(productoMapper.entityToResponse(productoR),HttpStatus.NO_CONTENT);
    }

    @Override
    @DeleteMapping("/{producto_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarProducto(@NotNull @PathVariable Long producto_id) {
        productoService.borrarProducto(producto_id);
    }


}
