package br.com.fiap.cliente.core.ports.in;

import br.com.fiap.cliente.core.domain.Cliente;

public interface ConsultarClienteServicePort {
    Cliente consultarCliente(String cpf);
}