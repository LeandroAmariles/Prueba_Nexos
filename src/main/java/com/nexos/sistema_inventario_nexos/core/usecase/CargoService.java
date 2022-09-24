package com.nexos.sistema_inventario_nexos.core.usecase;

import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.CargoResponse;

import java.util.List;


public interface CargoService {

    long createCargo(Cargo cargo);
    void deleteCargo(Long id);

    List<Cargo> getCargo();
}
