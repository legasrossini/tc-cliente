package br.com.fiap.cliente.core.ports.out;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;

import java.util.Optional;

public interface ClienteRepositoryPort {
    ClienteEntity save(ClienteEntity cliente);
    Optional<ClienteEntity> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}