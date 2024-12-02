package br.com.fiap.cliente.core.service;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.adapter.mappers.ClienteMapper;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.out.ClienteRepositoryPort;
import br.com.fiap.cliente.exceptions.ResourceAlreadyExistsException;
import br.com.fiap.cliente.exceptions.SystemUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalvarClienteServiceTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private SalvarClienteService salvarClienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarClienteWithValidCliente() {
        Cliente cliente = new Cliente("12345678900", "John Doe", "john.doe@example.com");
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(cliente.getCpf());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setEmail(cliente.getEmail());

        ClienteEntity savedClienteEntity = new ClienteEntity();
        savedClienteEntity.setCpf(cliente.getCpf());
        savedClienteEntity.setNome(cliente.getNome());
        savedClienteEntity.setEmail(cliente.getEmail());

        when(clienteRepositoryPort.existsByCpf(cliente.getCpf())).thenReturn(false);
        when(clienteMapper.toEntity(cliente)).thenReturn(clienteEntity);
        when(clienteRepositoryPort.save(clienteEntity)).thenReturn(savedClienteEntity);
        when(clienteMapper.toDomain(savedClienteEntity)).thenReturn(cliente);

        Cliente result = salvarClienteService.salvarCliente(cliente);

        assertNotNull(result, "O resultado não deve ser nulo");
        assertEquals("12345678900", result.getCpf());
        assertEquals("John Doe", result.getNome());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(clienteRepositoryPort, times(1)).existsByCpf(cliente.getCpf());
        verify(clienteRepositoryPort, times(1)).save(clienteEntity);
    }

    @Test
    void salvarClienteWithExistingCpf() {
        Cliente cliente = new Cliente("12345678900", "John Doe", "john.doe@example.com");

        when(clienteRepositoryPort.existsByCpf(cliente.getCpf())).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> salvarClienteService.salvarCliente(cliente));
        verify(clienteRepositoryPort, times(1)).existsByCpf(cliente.getCpf());
        verify(clienteRepositoryPort, never()).save(any(ClienteEntity.class));
    }

    @Test
    void salvarClienteWithException() {
        Cliente cliente = new Cliente("12345678900", "John Doe", "john.doe@example.com");

        when(clienteRepositoryPort.existsByCpf(cliente.getCpf())).thenThrow(new SystemUnavailableException());

        assertThrows(SystemUnavailableException.class, () -> salvarClienteService.salvarCliente(cliente));
        verify(clienteRepositoryPort, times(1)).existsByCpf(cliente.getCpf());
    }

    @Test
    void salvarClienteWithRepositoryException() {
        Cliente cliente = new Cliente("12345678900", "John Doe", "john.doe@example.com");
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(cliente.getCpf());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setEmail(cliente.getEmail());

        when(clienteRepositoryPort.existsByCpf(cliente.getCpf())).thenReturn(false);
        when(clienteMapper.toEntity(cliente)).thenReturn(clienteEntity);
        when(clienteRepositoryPort.save(clienteEntity)).thenThrow(new RuntimeException("Repository exception"));

        assertThrows(SystemUnavailableException.class, () -> salvarClienteService.salvarCliente(cliente));
    }

}