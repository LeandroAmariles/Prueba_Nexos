package com.nexos.sistema_inventario_nexos.ports.inputs.controller;

import com.nexos.sistema_inventario_nexos.core.model.Producto;
import com.nexos.sistema_inventario_nexos.core.model.ProductoList;
import com.nexos.sistema_inventario_nexos.core.usecase.ProductoService;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ApiConstants;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ProductoApi;
import com.nexos.sistema_inventario_nexos.ports.inputs.mapper.ProductoMapper;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UpdateProductRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductoResponse;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductosResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiConstants.PRODUCTOS_URI)
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

        return new ResponseEntity<>(productoMapper.entityToResponse(productoR), HttpStatus.NO_CONTENT);
    }

    @Override
    @DeleteMapping("/{producto_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarProducto(@NotNull @PathVariable Long producto_id) {
        productoService.borrarProducto(producto_id);
    }

    @Override
    @GetMapping
    public ResponseEntity<ProductosResponseList> getProductoList(@RequestParam Optional<Integer> page,
                                                                 @RequestParam Optional<Integer> size) {
        final int pageNumber = page.filter(p -> p >0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_SIZE);

        ProductoList list = productoService.getList(PageRequest.of(pageNumber, pageSize));

        ProductosResponseList response;
        {
            response = new ProductosResponseList();

            List<ProductoResponse> content = productoMapper.productoListToProductoResponse(list.getContent());

            response.setContent(content);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("nameproductfilter/{nombre_producto}")
    public ResponseEntity<ProductosResponseList> getProductoListNameFilter(@RequestParam Optional<Integer> page,
                                                                           @RequestParam Optional<Integer> size,
                                                                           @PathVariable String nombre_producto) {

        final int pageNumber = page.filter(p -> p >0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_SIZE);

        ProductoList list = productoService.getListNameFilter(PageRequest.of(pageNumber, pageSize), nombre_producto);

        ProductosResponseList response;

        {
            response = new ProductosResponseList();

            List<ProductoResponse> content = productoMapper.productoListToProductoResponse(list.getContent());

            response.setContent(content);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("userfilter/{nombre_usuario}")
    public ResponseEntity<ProductosResponseList> getProductoListUsuarioFilter(@RequestParam Optional<Integer> page,
                                                                              @RequestParam Optional<Integer> size,
                                                                              @PathVariable String nombre_usuario) {
        final int pageNumber = page.filter(p -> p >0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_SIZE);

        ProductoList list = productoService.getListNameUserFilter(PageRequest.of(pageNumber, pageSize), nombre_usuario);

        ProductosResponseList response;

        {
            response = new ProductosResponseList();

            List<ProductoResponse> content = productoMapper.productoListToProductoResponse(list.getContent());

            response.setContent(content);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
