package br.com.fiap.cliente.adapter.mappers;

import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toDomain(ClienteEntity clienteEntity);
    ClienteEntity toEntity(Cliente cliente);
}