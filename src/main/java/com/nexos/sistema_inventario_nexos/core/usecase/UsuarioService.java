package com.nexos.sistema_inventario_nexos.core.usecase;

import com.nexos.sistema_inventario_nexos.core.model.Usuario;


public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario, Long id);

    void borrarUsuario(Long id);
}
