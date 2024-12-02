package br.com.fiap.cliente.core.service;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.in.ConsultarClienteServicePort;
import br.com.fiap.cliente.core.ports.out.ClienteRepositoryPort;
import br.com.fiap.cliente.adapter.mappers.ClienteMapper;
import br.com.fiap.cliente.adapter.converters.ClienteConverter;
import br.com.fiap.cliente.exceptions.ResourceNotFoundException;
import br.com.fiap.cliente.exceptions.SystemUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultarClienteService implements ConsultarClienteServicePort {

    @Autowired
    private ClienteRepositoryPort clienteRepositoryPort;

    @Autowired
    private ClienteConverter clienteConverter;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Cliente consultarCliente(String cpf) {
        ClienteEntity clienteEntity = clienteRepositoryPort.findByCpf(cpf).orElseThrow(ResourceNotFoundException::new);
        try {
            return clienteMapper.toDomain(clienteEntity);
        } catch (Exception e) {
            throw new SystemUnavailableException();
        }
    }
}
