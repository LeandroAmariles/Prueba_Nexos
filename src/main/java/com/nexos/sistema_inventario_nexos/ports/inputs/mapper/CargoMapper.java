package com.nexos.sistema_inventario_nexos.ports.inputs.mapper;

import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateCargoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface CargoMapper {

    @Named("CreateCargo to Entity")
    Cargo cargoRequestToEntity(CreateCargoRequest request);
}
