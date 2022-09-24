package com.nexos.sistema_inventario_nexos.core.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductoList extends PageImpl<Producto> {

    public ProductoList(List<Producto> content, Pageable pageable, long total){
        super(content,pageable,total);
    }
}
