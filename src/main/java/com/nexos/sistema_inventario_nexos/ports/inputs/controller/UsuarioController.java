package com.nexos.sistema_inventario_nexos.ports.inputs.controller;

import com.nexos.sistema_inventario_nexos.core.model.Usuario;
import com.nexos.sistema_inventario_nexos.core.usecase.UsuarioService;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ApiConstants;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.UsuarioApi;
import com.nexos.sistema_inventario_nexos.ports.inputs.mapper.UsuarioMapper;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UsuarioRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.USUARIOS_URI)
@RequiredArgsConstructor
public class UsuarioController implements UsuarioApi {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;


    @Override
    @PostMapping("/{id_cargo}")
    public ResponseEntity<UsuarioResponse> addNewUser(@Valid @RequestBody UsuarioRequest request, @PathVariable Long id_cargo) {
        Usuario usuario = usuarioMapper.usuarioRequestToEntity(request);
        usuarioService.crearUsuario(usuario, id_cargo);
        UsuarioResponse usuarioR = usuarioMapper.entityToUserResponse(usuario);
        return new ResponseEntity<>(usuarioR, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(Long id) {
        usuarioService.borrarUsuario(id);
    }

    @Override
    @GetMapping
    public List<UsuarioResponse> getUsers() {
        return usuarioService.getUsers().stream().map((user) ->
                usuarioMapper.entityToUserResponse(user)).collect(Collectors.toList());

    }
}
