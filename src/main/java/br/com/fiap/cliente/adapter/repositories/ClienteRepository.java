package br.com.fiap.cliente.adapter.repositories;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.core.ports.out.ClienteRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepository implements ClienteRepositoryPort {

    @Autowired
    private DynamoDBRepository dynamoDBRepository;

    @Override
    public ClienteEntity save(ClienteEntity cliente) {
        return dynamoDBRepository.save(cliente);
    }

    @Override
    public Optional<ClienteEntity> findByCpf(String cpf) {
        return dynamoDBRepository.findById(cpf);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return dynamoDBRepository.existsById(cpf);
    }
}