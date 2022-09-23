package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateCargoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

public interface CargoApi {


    ResponseEntity<Void> createCargo(@Valid CreateCargoRequest request);

    void deleteCargo(@PathVariable Long id);
}
