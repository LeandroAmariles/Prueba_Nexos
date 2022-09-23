package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.ports.inputs.request.UsuarioRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UsuarioApi {

    ResponseEntity<UsuarioResponse> addNewUser(@Valid @RequestBody UsuarioRequest request, @PathVariable Long id);

    void deleteUser(@PathVariable Long id);
}
