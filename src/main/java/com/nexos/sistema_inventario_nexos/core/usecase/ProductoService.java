package com.nexos.sistema_inventario_nexos.core.usecase;

import com.nexos.sistema_inventario_nexos.core.model.Producto;

public interface ProductoService {

    Producto crearNuevoProducto(Producto producto, Long id) ;

    Producto actualizarProductoSiExiste(Producto producto, Long id);

    void borrarProducto(Long id);
}
