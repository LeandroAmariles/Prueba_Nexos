package com.nexos.sistema_inventario_nexos.core.usecase;

import com.nexos.sistema_inventario_nexos.core.model.Producto;
import com.nexos.sistema_inventario_nexos.core.model.ProductoList;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductosResponseList;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

public interface ProductoService {

    Producto crearNuevoProducto(Producto producto, Long id) ;

    Producto actualizarProductoSiExiste(Producto producto, Long id, Long id_usuario);

    void borrarProducto(Long id, Long id_usuario);

    ProductoList getList(PageRequest request);

    ProductoList getListNameFilter(PageRequest request, String nombre);

    ProductoList getListNameUserFilter(PageRequest requets, String nombre);

    ProductoList getListDateFilter(PageRequest request, String fecha);
}
