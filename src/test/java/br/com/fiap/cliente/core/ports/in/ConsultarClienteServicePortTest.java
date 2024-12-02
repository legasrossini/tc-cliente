package br.com.fiap.cliente.core.ports.in;

import br.com.fiap.cliente.core.domain.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConsultarClienteServicePortTest {

    @Test
    void consultarClienteWithValidCpf() {
        ConsultarClienteServicePort consultarClienteServicePort = mock(ConsultarClienteServicePort.class);
        String cpf = "12345678900";
        Cliente mockCliente = new Cliente(cpf, "John Doe", "john.doe@example.com");

        when(consultarClienteServicePort.consultarCliente(cpf)).thenReturn(mockCliente);

        Cliente result = consultarClienteServicePort.consultarCliente(cpf);
        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
    }

    @Test
    void consultarClienteWithInvalidCpf() {
        ConsultarClienteServicePort consultarClienteServicePort = mock(ConsultarClienteServicePort.class);
        String cpf = "invalid_cpf";

        when(consultarClienteServicePort.consultarCliente(cpf)).thenReturn(null);

        Cliente result = consultarClienteServicePort.consultarCliente(cpf);
        assertNull(result);
    }

    @Test
    void consultarClienteWithNullCpf() {
        ConsultarClienteServicePort consultarClienteServicePort = mock(ConsultarClienteServicePort.class);

        when(consultarClienteServicePort.consultarCliente(null)).thenReturn(null);

        Cliente result = consultarClienteServicePort.consultarCliente(null);
        assertNull(result);
    }
}