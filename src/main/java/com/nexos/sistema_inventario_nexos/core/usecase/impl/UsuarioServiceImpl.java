package com.nexos.sistema_inventario_nexos.core.usecase.impl;

import com.nexos.sistema_inventario_nexos.config.exception.ConflictException;
import com.nexos.sistema_inventario_nexos.core.model.Usuario;
import com.nexos.sistema_inventario_nexos.core.repository.CargoRepository;
import com.nexos.sistema_inventario_nexos.core.repository.UsuarioRepository;
import com.nexos.sistema_inventario_nexos.core.usecase.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final CargoRepository cargoRepository;


    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario, Long id) {
        if (existUser(usuario.getNombre()) || !cargoRepository.existsById(id)) {
            throw new ConflictException("There is already a user with name " + usuario.getNombre() +" or cargo dont exits");
        }
        usuario.setCargo(cargoRepository.findById(id).get());
        return usuarioRepository.save(usuario);

    }

    @Override
    public void borrarUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(usuarioRepository :: delete);
    }

    private Boolean existUser(String nombre) {
        return usuarioRepository.existsByNombre(nombre);
    }
}
