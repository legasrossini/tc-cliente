package br.com.fiap.cliente.adapter.converters;

import br.com.fiap.cliente.adapter.dtos.ClienteDto;
import br.com.fiap.cliente.core.domain.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClienteConverterTest {

    private final ClienteConverter clienteConverter = new ClienteConverter();

    @Test
    void convertToDomain() {
        ClienteDto clienteDto = new ClienteDto("12345678900", "John Doe", "john.doe@example.com");
        Cliente cliente = clienteConverter.toDomain(clienteDto);

        assertEquals("12345678900", cliente.getCpf());
        assertEquals("John Doe", cliente.getNome());
        assertEquals("john.doe@example.com", cliente.getEmail());
    }

    @Test
    void convertToDomainWithNullValues() {
        ClienteDto clienteDto = new ClienteDto(null, null, null);
        Cliente cliente = clienteConverter.toDomain(clienteDto);

        assertNull(cliente.getCpf());
        assertNull(cliente.getNome());
        assertNull(cliente.getEmail());
    }

    @Test
    void convertToDto() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setNome("John Doe");
        cliente.setEmail("john.doe@example.com");
        ClienteDto clienteDto = clienteConverter.toDto(cliente);

        assertEquals("12345678900", clienteDto.getCpf());
        assertEquals("John Doe", clienteDto.getNome());
        assertEquals("john.doe@example.com", clienteDto.getEmail());
    }

    @Test
    void convertToDtoWithNullValues() {
        Cliente cliente = new Cliente();
        cliente.setCpf(null);
        cliente.setNome(null);
        cliente.setEmail(null);
        ClienteDto clienteDto = clienteConverter.toDto(cliente);

        assertNull(clienteDto.getCpf());
        assertNull(clienteDto.getNome());
        assertNull(clienteDto.getEmail());
    }
}