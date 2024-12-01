package br.com.fiap.cliente.core.ports.in;

import br.com.fiap.cliente.core.domain.Cliente;

public interface ClienteServicePort {
    Cliente salvarCliente(Cliente cliente);
    Cliente consultarCliente(String cpf);
}