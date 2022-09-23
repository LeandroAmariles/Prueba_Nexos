package com.nexos.sistema_inventario_nexos.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConflictException extends RuntimeException{

    private final String conflictMessage;
}
