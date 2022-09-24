package com.nexos.sistema_inventario_nexos.ports.inputs.mapper;

import com.nexos.sistema_inventario_nexos.core.model.Producto;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UpdateProductRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductoMapper {

    Producto createProductRequestToEntity(CreateProductoRequest request);

    ProductoResponse entityToResponse(Producto producto);

    Producto updateProductToEntity(UpdateProductRequest request);

    List<ProductoResponse> productoListToProductoResponse(List<Producto> productoList);

}
