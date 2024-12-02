package br.com.fiap.cliente.adapter.controllers;

import br.com.fiap.cliente.adapter.converters.ClienteConverter;
import br.com.fiap.cliente.adapter.dtos.ClienteDto;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.in.ConsultarClienteServicePort;
import br.com.fiap.cliente.core.ports.in.SalvarClienteServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.cliente.adapter.constants.MessageConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private final ClienteConverter clienteConverter;
    @Autowired
    private final SalvarClienteServicePort salvarCliente;
    @Autowired
    private final ConsultarClienteServicePort consultarClienteServicePort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto createCliente(@Valid @RequestBody ClienteDto clienteDto) {
        logger.info(INICIANDO_REQUISICAO_CRIAR_CLIENTE);
        Cliente cliente = salvarCliente.salvarCliente(clienteConverter.toDomain(clienteDto));
        ClienteDto response = clienteConverter.toDto(cliente);
        logger.info(FINALIZANDO_REQUISICAO_CRIAR_CLIENTE);
        return response;
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto getCliente(@PathVariable("cpf") String cpf) {
        logger.info(INICIANDO_REQUISICAO_OBTER_CLIENTE, cpf);
        Cliente cliente = consultarClienteServicePort.consultarCliente(cpf);
        ClienteDto response = clienteConverter.toDto(cliente);
        logger.info(FINALIZANDO_REQUISICAO_OBTER_CLIENTE, cpf);
        return response;
    }
}