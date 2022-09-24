package com.nexos.sistema_inventario_nexos.config.exception.handler;

import com.nexos.sistema_inventario_nexos.config.exception.AuthenticationException;
import com.nexos.sistema_inventario_nexos.config.exception.ConflictException;
import com.nexos.sistema_inventario_nexos.config.exception.CreateException;
import com.nexos.sistema_inventario_nexos.config.exception.NotFoundException;
import com.nexos.sistema_inventario_nexos.config.exception.error.ErrorCode;
import com.nexos.sistema_inventario_nexos.config.exception.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorDetails> handleNotFound(NotFoundException ex){
        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail("The resource with id %s is not found.".formatted(ex.getResourceId()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<ErrorDetails> handleConflict(ConflictException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_ALREADY_EXISTS)
                .detail(ex.getConflictMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CreateException.class)
    private ResponseEntity<ErrorDetails> handleCreateConflict(CreateException ex){
        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.BAD_REQUEST)
                .detail(ex.getConflictMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<ErrorDetails> handleAuthenticationException(AuthenticationException ex){
        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.BAD_CREDENTIALS)
                .detail(ex.getAuthenticationMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
