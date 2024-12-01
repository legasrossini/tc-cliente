package br.com.fiap.cliente.adapter.repositories;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface DynamoDBRepository extends CrudRepository<ClienteEntity, String> {
}