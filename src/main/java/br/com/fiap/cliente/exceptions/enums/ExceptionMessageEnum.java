package br.com.fiap.cliente.exceptions.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessageEnum {
    INVALID_PARAMETER("001", "Parâmetro inválido"),
    RESOURCE_NOT_FOUND("002", "Recurso não encontrado"),
    RESOURCE_ALREADY_EXISTS("003", "Recurso já cadastrado"),
    SYSTEM_UNAVAILABLE("004", "Sistema indisponível"),
    VALIDATION_ERROR("005", "Erro de validação");

    private final String code;
    private final String error;
}