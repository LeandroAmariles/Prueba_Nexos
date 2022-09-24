package com.nexos.sistema_inventario_nexos.ports.inputs.api;

import com.nexos.sistema_inventario_nexos.config.exception.error.ErrorDetails;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.CreateProductoRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.request.UpdateProductRequest;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductoResponse;
import com.nexos.sistema_inventario_nexos.ports.inputs.response.ProductosResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ProductoApi {


    @Operation(summary = "New product", description = "Create a new product", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class),
                            examples = @ExampleObject(value = "{\"code\":\"INVALID_FIELD_VALUE\",\"detail\":\"Dont can creates a new product, The user dont exist\"\"}"))})})
    ResponseEntity<ProductoResponse> createProducto(@Valid @RequestBody CreateProductoRequest request, @PathVariable Long id_usuario);

    @Operation(summary = "Updated Producto", description = "Updated the date or amount of exists product ", responses = {
            @ApiResponse(responseCode = "204", description = "Not Content"),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class),
                            examples = @ExampleObject(value = "{\"code\":\"RESOURCE_NOT_FOUND\",\"detail\":\"The resource with id is not found\"\"}"))})})
    ResponseEntity<ProductoResponse> updateProduct(@Valid @RequestBody UpdateProductRequest request, @NotNull @PathVariable Long id_producto,
                                                   @NotNull @PathVariable Long id_user);

    @Operation(summary = "Delete a product", description = "Delete a product if exist and the user is allowed ", responses = {
            @ApiResponse(responseCode = "204", description = "Not Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class),
                            examples = @ExampleObject(value = "{\"code\":\"BAD_CREDENTIALS\",\"detail\":\"The server cannot return a response due to invalid credentials.\"\"}"))})})
    void deleteProduct(@NotNull @PathVariable Long id, @NotNull @PathVariable Long id_usuario);

    @Operation(summary = "List Products", description = "List all Products ", responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductosResponseList.class))})})
    ResponseEntity<ProductosResponseList> getProductoList(@RequestParam Optional<Integer> page,
                                                          @RequestParam Optional<Integer> size);


    @Operation(summary = "List Products Name Product filter", description = "List all Products for a product name ", responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductosResponseList.class))})})
    ResponseEntity<ProductosResponseList> getProductoListNameFilter(@RequestParam Optional<Integer> page,
                                                                    @RequestParam Optional<Integer> size,
                                                                    @PathVariable String nombre_producto);

    @Operation(summary = "List Products Name User filter", description = "List all Products for a user name ", responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductosResponseList.class))})})
    ResponseEntity<ProductosResponseList> getProductoListUsuarioFilter(@RequestParam Optional<Integer> page,
                                                                       @RequestParam Optional<Integer> size,
                                                                       @PathVariable String nombre_usuario);

    @Operation(summary = "List Products Date Product filter", description = "List all Products for a product date ", responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductosResponseList.class))})})
    ResponseEntity<ProductosResponseList> getProductoListDateFIlter(@RequestParam Optional<Integer> page,
                                                                    @RequestParam Optional<Integer> size,
                                                                    @PathVariable String fecha);
}
