package br.com.fiap.cliente.exceptions;

import br.com.fiap.cliente.exceptions.enums.ExceptionMessageEnum;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String code;

    public ResourceNotFoundException() {
        super(ExceptionMessageEnum.RESOURCE_NOT_FOUND.getError());
        this.code = ExceptionMessageEnum.RESOURCE_NOT_FOUND.getCode();
    }
}