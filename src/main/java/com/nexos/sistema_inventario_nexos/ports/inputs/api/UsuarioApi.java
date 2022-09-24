package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.config.exception.error.ErrorDetails;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UsuarioRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductosResponseList;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface UsuarioApi {

    @Operation(summary = "Create New User", description = "Creates a new user if dont exits", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioResponse.class))}),
    @ApiResponse(responseCode = "409", description = "Conflict",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(value = "{\"code\":\"RESOURCE_ALREADY_EXISTS\",\"detail\":\"There is already an user with that name\"}"))})})
    ResponseEntity<UsuarioResponse> addNewUser(@Valid @RequestBody UsuarioRequest request, @PathVariable Long id);

    @Operation(summary = "Delete a User", description = "Delete a user", responses = {
            @ApiResponse(responseCode = "204", description = "Not Content")})

    void deleteUser(@PathVariable Long id);

    @Operation(summary = "Get Users", description = "Get all created Users", responses = {
            @ApiResponse(responseCode = "200", description = "Ok")})
    List<UsuarioResponse> getUsers();
}
