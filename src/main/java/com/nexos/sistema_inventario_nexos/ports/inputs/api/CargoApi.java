package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateCargoRequest;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface CargoApi {


    ResponseEntity<Void> createCargo(@Valid CreateCargoRequest request);
}
