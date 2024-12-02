package br.com.fiap.cliente.adapter.mappers;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.core.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toDomain(ClienteEntity clienteEntity);
    ClienteEntity toEntity(Cliente cliente);
}