package br.com.fiap.cliente.adapter.mappers;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.adapter.mappers.ClienteMapper;
import br.com.fiap.cliente.core.domain.Cliente;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteMapperTest {

    private final ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);

    @Test
    void toDomain_shouldMapClienteEntityToCliente() {
        ClienteEntity entity = new ClienteEntity();
        entity.setCpf("43295042861");
        entity.setNome("John Doe");
        entity.setEmail("dlegasr@gmail.com");

        Cliente domain = mapper.toDomain(entity);

        assertEquals("43295042861", domain.getCpf());
        assertEquals("John Doe", domain.getNome());
        assertEquals("dlegasr@gmail.com", domain.getEmail());
    }

    @Test
    void toDomain_shouldReturnNullWhenClienteEntityIsNull() {
        Cliente domain = mapper.toDomain(null);

        assertNull(domain);
    }

    @Test
    void toEntity_shouldMapClienteToClienteEntity() {
        Cliente domain = new Cliente();
        domain.setCpf("43295042861");
        domain.setNome("John Doe");
        domain.setEmail("dlegasr@gmail.com");

        ClienteEntity entity = mapper.toEntity(domain);

        assertEquals("43295042861", entity.getCpf());
        assertEquals("John Doe", entity.getNome());
        assertEquals("dlegasr@gmail.com", entity.getEmail());
    }

    @Test
    void toEntity_shouldReturnNullWhenClienteIsNull() {
        ClienteEntity entity = mapper.toEntity(null);

        assertNull(entity);
    }
}