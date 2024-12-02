package br.com.fiap.cliente.exceptions;

import br.com.fiap.cliente.exceptions.enums.ExceptionMessageEnum;
import lombok.Getter;

@Getter
public class SystemUnavailableException extends RuntimeException {
    private final String code;

    public SystemUnavailableException() {
        super(ExceptionMessageEnum.SYSTEM_UNAVAILABLE.getError());
        this.code = ExceptionMessageEnum.SYSTEM_UNAVAILABLE.getCode();
    }
}