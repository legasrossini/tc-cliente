package br.com.fiap.cliente.core.service;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.in.SalvarClienteServicePort;
import br.com.fiap.cliente.core.ports.out.ClienteRepositoryPort;
import br.com.fiap.cliente.adapter.mappers.ClienteMapper;
import br.com.fiap.cliente.adapter.converters.ClienteConverter;
import br.com.fiap.cliente.exceptions.ResourceAlreadyExistsException;
import br.com.fiap.cliente.exceptions.SystemUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalvarClienteService implements SalvarClienteServicePort {

    @Autowired
    private ClienteRepositoryPort clienteRepositoryPort;

    @Autowired
    private ClienteConverter clienteConverter;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        if (clienteRepositoryPort.existsByCpf(cliente.getCpf())) {
            throw new ResourceAlreadyExistsException();
        }
        try {
            ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
            clienteEntity = clienteRepositoryPort.save(clienteEntity);
            return clienteMapper.toDomain(clienteEntity);
        } catch (Exception e) {
            throw new SystemUnavailableException();
        }
    }
}