package br.com.fiap.cliente.adapter.controllers;

import br.com.fiap.cliente.adapter.converters.ClienteConverter;
import br.com.fiap.cliente.adapter.dtos.ClienteDto;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.in.ConsultarClienteServicePort;
import br.com.fiap.cliente.core.ports.in.SalvarClienteServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteConverter clienteConverter;

    @Mock
    private SalvarClienteServicePort salvarCliente;

    @Mock
    private ConsultarClienteServicePort consultarClienteServicePort;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCliente() {
        ClienteDto clienteDto = new ClienteDto();
        Cliente cliente = new Cliente();
        ClienteDto responseDto = new ClienteDto();

        when(clienteConverter.toDomain(clienteDto)).thenReturn(cliente);
        when(salvarCliente.salvarCliente(cliente)).thenReturn(cliente);
        when(clienteConverter.toDto(cliente)).thenReturn(responseDto);

        ClienteDto result = clienteController.createCliente(clienteDto);

        assertEquals(responseDto, result);
        verify(clienteConverter, times(1)).toDomain(clienteDto);
        verify(salvarCliente, times(1)).salvarCliente(cliente);
        verify(clienteConverter, times(1)).toDto(cliente);
    }

    @Test
    void testGetCliente() {
        String cpf = "12345678900";
        Cliente cliente = new Cliente();
        ClienteDto responseDto = new ClienteDto();

        when(consultarClienteServicePort.consultarCliente(cpf)).thenReturn(cliente);
        when(clienteConverter.toDto(cliente)).thenReturn(responseDto);

        ClienteDto result = clienteController.getCliente(cpf);

        assertEquals(responseDto, result);
        verify(consultarClienteServicePort, times(1)).consultarCliente(cpf);
        verify(clienteConverter, times(1)).toDto(cliente);
    }
}