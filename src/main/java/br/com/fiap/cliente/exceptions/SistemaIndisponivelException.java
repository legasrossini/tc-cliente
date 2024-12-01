package br.com.fiap.cliente.exceptions;

public class SistemaIndisponivelException extends RuntimeException {
    public SistemaIndisponivelException(String message) {
        super(message);
    }
}