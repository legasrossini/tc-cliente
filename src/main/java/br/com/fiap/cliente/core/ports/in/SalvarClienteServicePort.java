package br.com.fiap.cliente.core.ports.in;

import br.com.fiap.cliente.core.domain.Cliente;

public interface SalvarClienteServicePort {
    Cliente salvarCliente(Cliente cliente);
}