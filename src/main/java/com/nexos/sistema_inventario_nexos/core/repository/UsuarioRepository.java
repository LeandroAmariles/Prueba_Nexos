package com.nexos.sistema_inventario_nexos.core.repository;

import com.nexos.sistema_inventario_nexos.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Boolean existsByNombre(String nombre);
}
