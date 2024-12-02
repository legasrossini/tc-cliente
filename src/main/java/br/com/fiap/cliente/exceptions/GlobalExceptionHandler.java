package br.com.fiap.cliente.exceptions;

import br.com.fiap.cliente.exceptions.dtos.ErrorDto;
import br.com.fiap.cliente.exceptions.dtos.FieldErrorDto;
import br.com.fiap.cliente.exceptions.enums.ExceptionMessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation exception: {}", ex.getMessage(), ex);
        List<FieldErrorDto> fieldErrors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> new FieldErrorDto(((FieldError) error).getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        ExceptionMessageEnum messageEnum = ExceptionMessageEnum.VALIDATION_ERROR;
        ErrorDto errorResponseDto = new ErrorDto(messageEnum.getCode(), messageEnum.getError(), fieldErrors);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorDto> handleInvalidParameterException(InvalidParameterException ex) {
        logger.error("Invalid parameter exception: {}", ex.getMessage(), ex);
        ExceptionMessageEnum messageEnum = ExceptionMessageEnum.INVALID_PARAMETER;
        ErrorDto errorDto = new ErrorDto(messageEnum.getCode(), messageEnum.getError());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("Resource not found exception: {}", ex.getMessage(), ex);
        ExceptionMessageEnum messageEnum = ExceptionMessageEnum.RESOURCE_NOT_FOUND;
        ErrorDto errorDto = new ErrorDto(messageEnum.getCode(), messageEnum.getError());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        logger.error("Resource already exists exception: {}", ex.getMessage(), ex);
        ExceptionMessageEnum messageEnum = ExceptionMessageEnum.RESOURCE_ALREADY_EXISTS;
        ErrorDto errorDto = new ErrorDto(messageEnum.getCode(), messageEnum.getError());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SystemUnavailableException.class)
    public ResponseEntity<ErrorDto> handleSystemUnavailableException(SystemUnavailableException ex) {
        logger.error("System unavailable exception: {}", ex.getMessage(), ex);
        ExceptionMessageEnum messageEnum = ExceptionMessageEnum.SYSTEM_UNAVAILABLE;
        ErrorDto errorDto = new ErrorDto(messageEnum.getCode(), messageEnum.getError());
        return new ResponseEntity<>(errorDto, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        logger.error("Generic exception: {}", ex.getMessage(), ex);
        ErrorDto errorDto = new ErrorDto("000", ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}