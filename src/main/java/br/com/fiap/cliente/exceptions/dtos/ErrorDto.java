package br.com.fiap.cliente.exceptions.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {
    private final String code;
    private final String error;
    private final List<FieldErrorDto> fields;

    public ErrorDto(String code, String error) {
        this.code = code;
        this.error = error;
        this.fields = null;
    }
}