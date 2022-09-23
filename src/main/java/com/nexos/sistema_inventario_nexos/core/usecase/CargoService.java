package com.nexos.sistema_inventario_nexos.core.usecase;

import com.nexos.sistema_inventario_nexos.core.model.Cargo;


public interface CargoService {

    long createCargo(Cargo cargo);
    void deleteCargo(Long id);
}
