package com.nexos.sistema_inventario_nexos.core.usecase;

import com.nexos.sistema_inventario_nexos.core.model.Usuario;

import java.util.List;


public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario, Long id);

    void borrarUsuario(Long id);

    List<Usuario> getUsers();
}
