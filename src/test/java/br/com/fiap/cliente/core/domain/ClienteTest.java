package br.com.fiap.cliente.core.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClienteTest {

    @Test
    void createClienteWithAllFields() {
        Cliente cliente = new Cliente("12345678900", "John Doe", "john.doe@example.com");

        assertEquals("12345678900", cliente.getCpf());
        assertEquals("John Doe", cliente.getNome());
        assertEquals("john.doe@example.com", cliente.getEmail());
    }

    @Test
    void createClienteWithNoArgsConstructor() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setNome("John Doe");
        cliente.setEmail("john.doe@example.com");

        assertEquals("12345678900", cliente.getCpf());
        assertEquals("John Doe", cliente.getNome());
        assertEquals("john.doe@example.com", cliente.getEmail());
    }

    @Test
    void createClienteWithNullValues() {
        Cliente cliente = new Cliente(null, null, null);

        assertNull(cliente.getCpf());
        assertNull(cliente.getNome());
        assertNull(cliente.getEmail());
    }

    @Test
    void setAndGetCpf() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");

        assertEquals("12345678900", cliente.getCpf());
    }

    @Test
    void setAndGetNome() {
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");

        assertEquals("John Doe", cliente.getNome());
    }

    @Test
    void setAndGetEmail() {
        Cliente cliente = new Cliente();
        cliente.setEmail("john.doe@example.com");

        assertEquals("john.doe@example.com", cliente.getEmail());
    }
}