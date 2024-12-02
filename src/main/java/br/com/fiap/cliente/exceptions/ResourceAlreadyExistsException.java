package br.com.fiap.cliente.exceptions;

import br.com.fiap.cliente.exceptions.enums.ExceptionMessageEnum;
import lombok.Getter;

@Getter
public class ResourceAlreadyExistsException extends RuntimeException {
    private final String code;

    public ResourceAlreadyExistsException() {
        super(ExceptionMessageEnum.RESOURCE_ALREADY_EXISTS.getError());
        this.code = ExceptionMessageEnum.RESOURCE_ALREADY_EXISTS.getCode();
    }
}