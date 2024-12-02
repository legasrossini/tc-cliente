package br.com.fiap.cliente.adapter.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    @NotBlank(message = "CPF não pode ser nulo ou vazio")
    @CPF(message = "CPF inválido")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos e não pode ter pontos ou traços")
    private String cpf;
    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    private String nome;
    @NotBlank(message = "Email não pode ser nulo ou vazio")
    private String email;
}