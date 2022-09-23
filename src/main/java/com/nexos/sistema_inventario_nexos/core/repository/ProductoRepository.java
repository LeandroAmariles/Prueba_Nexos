package com.nexos.sistema_inventario_nexos.core.repository;

import com.nexos.sistema_inventario_nexos.core.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
