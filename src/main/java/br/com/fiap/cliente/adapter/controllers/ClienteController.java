package br.com.fiap.cliente.adapter.controllers;


import br.com.fiap.cliente.adapter.converters.ClienteConverter;
import br.com.fiap.cliente.adapter.dtos.ClienteDto;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.in.ClienteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteConverter clienteConverter;
    private final ClienteServicePort clientePort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto createCliente(@RequestBody ClienteDto clienteDto) {
        Cliente cliente = clientePort.salvarCliente(clienteConverter.toDomain(clienteDto));
        return clienteConverter.toDto(cliente);
    }
}