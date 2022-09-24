package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.config.exception.error.ErrorDetails;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateCargoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.CargoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

public interface CargoApi {

    @Operation(summary = "new Cargo", description = "Creates a new cargo", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class),
                            examples = @ExampleObject(value = "{\"code\":\"RESOURCE_ALREADY_EXISTS\",\"detail\":\"There is already a cargo with name: \"}"))})
    })
    ResponseEntity<Void> createCargo(@Valid CreateCargoRequest request);

    @Operation(summary = "Delete Cargo", description = "Deletes a Cargo if exits", responses = {
            @ApiResponse(responseCode = "204", description = "Not Content")})
    void deleteCargo(@PathVariable Long id);

    @Operation(summary = "Get Cargos", description = "Get all created Cargos", responses = {
            @ApiResponse(responseCode = "200", description = "Ok")})
    List<CargoResponse> getCargos();
}
