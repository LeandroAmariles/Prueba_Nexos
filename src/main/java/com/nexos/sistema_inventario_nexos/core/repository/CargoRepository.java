package com.nexos.sistema_inventario_nexos.core.repository;

import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Boolean existsByNombre(String nombre);

    Boolean existsById(long id);
}
