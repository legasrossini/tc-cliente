package br.com.fiap.cliente.exceptions;

import br.com.fiap.cliente.exceptions.dtos.ErrorDto;
import br.com.fiap.cliente.exceptions.enums.ExceptionMessageEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }
    @Test
    void handleInvalidParameterException_shouldReturnBadRequest() {
        InvalidParameterException ex = new InvalidParameterException();

        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleInvalidParameterException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ExceptionMessageEnum.INVALID_PARAMETER.getCode(), response.getBody().getCode());
    }

    @Test
    void handleResourceNotFoundException_shouldReturnNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException();

        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleResourceNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ExceptionMessageEnum.RESOURCE_NOT_FOUND.getCode(), response.getBody().getCode());
    }

    @Test
    void handleResourceAlreadyExistsException_shouldReturnConflict() {
        ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException();

        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleResourceAlreadyExistsException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(ExceptionMessageEnum.RESOURCE_ALREADY_EXISTS.getCode(), response.getBody().getCode());
    }

    @Test
    void handleSystemUnavailableException_shouldReturnServiceUnavailable() {
        SystemUnavailableException ex = new SystemUnavailableException();

        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleSystemUnavailableException(ex);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals(ExceptionMessageEnum.SYSTEM_UNAVAILABLE.getCode(), response.getBody().getCode());
    }

    @Test
    void handleGenericException_shouldReturnInternalServerError() {
        Exception ex = new Exception("Generic error");

        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("000", response.getBody().getCode());
    }
}