package br.com.fiap.cliente.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorDto {
    private final String field;
    private final String erro;
}