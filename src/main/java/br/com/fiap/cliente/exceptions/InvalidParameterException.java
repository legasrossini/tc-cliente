package br.com.fiap.cliente.exceptions;

import br.com.fiap.cliente.exceptions.enums.ExceptionMessageEnum;
import lombok.Getter;

@Getter
public class InvalidParameterException extends RuntimeException {
    private final String code;

    public InvalidParameterException() {
        super(ExceptionMessageEnum.INVALID_PARAMETER.getError());
        this.code = ExceptionMessageEnum.INVALID_PARAMETER.getCode();
    }
}