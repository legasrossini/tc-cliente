package br.com.fiap.cliente.core.ports.in;

import br.com.fiap.cliente.core.domain.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalvarClienteServicePortTest {

    @Test
    void salvarClienteWithValidCliente() {
        SalvarClienteServicePort salvarClienteServicePort = mock(SalvarClienteServicePort.class);
        Cliente cliente = new Cliente("12345678900", "John Doe", "john.doe@example.com");

        when(salvarClienteServicePort.salvarCliente(cliente)).thenReturn(cliente);

        Cliente result = salvarClienteServicePort.salvarCliente(cliente);
        assertNotNull(result);
        assertEquals("12345678900", result.getCpf());
        assertEquals("John Doe", result.getNome());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    void salvarClienteWithNullValues() {
        SalvarClienteServicePort salvarClienteServicePort = mock(SalvarClienteServicePort.class);
        Cliente cliente = new Cliente(null, null, null);

        when(salvarClienteServicePort.salvarCliente(cliente)).thenReturn(cliente);

        Cliente result = salvarClienteServicePort.salvarCliente(cliente);
        assertNotNull(result);
        assertNull(result.getCpf());
        assertNull(result.getNome());
        assertNull(result.getEmail());
    }

    @Test
    void salvarClienteWithEmptyFields() {
        SalvarClienteServicePort salvarClienteServicePort = mock(SalvarClienteServicePort.class);
        Cliente cliente = new Cliente("", "", "");

        when(salvarClienteServicePort.salvarCliente(cliente)).thenReturn(cliente);

        Cliente result = salvarClienteServicePort.salvarCliente(cliente);
        assertNotNull(result);
        assertEquals("", result.getCpf());
        assertEquals("", result.getNome());
        assertEquals("", result.getEmail());
    }
}