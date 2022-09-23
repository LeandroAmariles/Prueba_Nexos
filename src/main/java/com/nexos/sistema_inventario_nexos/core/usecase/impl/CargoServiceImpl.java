package com.nexos.sistema_inventario_nexos.core.usecase.impl;

import com.nexos.sistema_inventario_nexos.config.exception.ConflictException;
import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import com.nexos.sistema_inventario_nexos.core.repository.CargoRepository;
import com.nexos.sistema_inventario_nexos.core.usecase.CargoService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;


    @Override
    @Transactional
    public long createCargo(Cargo cargo) {
        if(existsCargo(cargo.getNombre())){

            throw new ConflictException("There is already a cargo with name: "+ cargo.getNombre());
        }
        System.out.println(cargo.getNombre());
        return cargoRepository.save(cargo).getId();
    }

    @Override
    @Transactional
    public void deleteCargo(Long id) {
        cargoRepository.findById(id).ifPresent(cargoRepository ::delete);
    }

    private Boolean existsCargo(String nombre){
        return cargoRepository.existsByNombre(nombre);
    }
}
