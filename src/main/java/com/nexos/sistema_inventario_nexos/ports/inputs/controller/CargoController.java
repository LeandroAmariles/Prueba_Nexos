package com.nexos.sistema_inventario_nexos.ports.inputs.controller;

import com.nexos.sistema_inventario_nexos.core.model.Cargo;
import com.nexos.sistema_inventario_nexos.core.usecase.CargoService;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.ApiConstants;
import com.nexos.sistema_inventario_nexos.ports.inputs.api.CargoApi;
import com.nexos.sistema_inventario_nexos.ports.inputs.mapper.CargoMapper;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateCargoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(ApiConstants.CARGO_URI)
@RequiredArgsConstructor
public class CargoController implements CargoApi {

    private final CargoMapper cargoMapper;

    private final CargoService cargoService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCargo(@Valid @RequestBody CreateCargoRequest request) {

        Cargo cargo = cargoMapper.cargoRequestToEntity(request);

        final long id = cargoService.createCargo(cargo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCargo(@PathVariable  Long id) {
          cargoService.deleteCargo(id);
    }
}
