package br.com.fiap.cliente.adapter.converters;

import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.adapter.dtos.ClienteDto;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter {

    public Cliente toDomain(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteDto.getCpf());
        cliente.setNome(clienteDto.getNome());
        cliente.setEmail(clienteDto.getEmail());
        return cliente;
    }

    public ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getCpf(), cliente.getNome(), cliente.getEmail());
    }
}