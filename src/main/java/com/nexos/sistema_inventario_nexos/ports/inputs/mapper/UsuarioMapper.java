package com.nexos.sistema_inventario_nexos.ports.inputs.mapper;

import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import com.nexos.sistema_inventario_nexos.core.model.Usuario;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UsuarioRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.UsuarioResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper {

    Usuario usuarioRequestToEntity(UsuarioRequest request);

    UsuarioResponse entityToUserResponse(Usuario user);
}
