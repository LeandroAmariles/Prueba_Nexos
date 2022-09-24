package com.nexos.sistema_inventario_nexos.config.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_FIELD_VALUE("The provided field is not valid"),
    RESOURCE_NOT_FOUND("The requested resource was not found"),
    BAD_REQUEST("The server cannot return a response due to an error on the client's end"),
    RESOURCE_ALREADY_EXISTS("This resource already exists"),
    BAD_CREDENTIALS("The user is not allowed to do this"),
    ILLEGAL_ARGUMENT("Has been passed an illegal or inappropriate argument");

    private final String defaultMessage;
}
