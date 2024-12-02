package br.com.fiap.cliente.core.service;

import br.com.fiap.cliente.adapter.converters.ClienteConverter;
import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.adapter.mappers.ClienteMapper;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.out.ClienteRepositoryPort;
import br.com.fiap.cliente.exceptions.ResourceNotFoundException;
import br.com.fiap.cliente.exceptions.SystemUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarClienteServiceTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    @Mock
    private ClienteConverter clienteConverter;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ConsultarClienteService consultarClienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void consultarClienteWithValidCpf() {
        String cpf = "12345678900";
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(cpf);
        clienteEntity.setNome("John Doe");
        clienteEntity.setEmail("john.doe@example.com");

        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome("John Doe");
        cliente.setEmail("john.doe@example.com");

        when(clienteRepositoryPort.findByCpf(cpf)).thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.toDomain(clienteEntity)).thenReturn(cliente);

        Cliente result = consultarClienteService.consultarCliente(cpf);

        assertNotNull(result);
        assertEquals(cliente, result);
        verify(clienteRepositoryPort, times(1)).findByCpf(cpf);

    }

    @Test
    void consultarClienteWithInvalidCpf() {
        String cpf = "invalid_cpf";

        when(clienteRepositoryPort.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> consultarClienteService.consultarCliente(cpf));
        verify(clienteRepositoryPort, times(1)).findByCpf(cpf);
    }

    @Test
    void consultarClienteWithException() {
        String cpf = "12345678900";

        when(clienteRepositoryPort.findByCpf(cpf)).thenThrow(new SystemUnavailableException());

        assertThrows(SystemUnavailableException.class, () -> consultarClienteService.consultarCliente(cpf));
        verify(clienteRepositoryPort, times(1)).findByCpf(cpf);
    }

    @Test
    void consultarClienteWithMapperException() {
        String cpf = "12345678900";
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(cpf);

        when(clienteRepositoryPort.findByCpf(cpf)).thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.toDomain(clienteEntity)).thenThrow(new RuntimeException("Mapper exception"));

        SystemUnavailableException exception = assertThrows(SystemUnavailableException.class,
                () -> consultarClienteService.consultarCliente(cpf));

        verify(clienteRepositoryPort, times(1)).findByCpf(cpf);
        verify(clienteMapper, times(1)).toDomain(clienteEntity);
    }


}