package br.com.fiap.cliente.core.service;

import br.com.fiap.cliente.adapter.entities.ClienteEntity;
import br.com.fiap.cliente.core.domain.Cliente;
import br.com.fiap.cliente.core.ports.in.ClienteServicePort;
import br.com.fiap.cliente.core.ports.out.ClienteRepositoryPort;
import br.com.fiap.cliente.adapter.mappers.ClienteMapper;
import br.com.fiap.cliente.adapter.converters.ClienteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements ClienteServicePort {

    @Autowired
    private ClienteRepositoryPort clienteRepositoryPort;

    @Autowired
    private ClienteConverter clienteConverter;

    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        ClienteEntity savedEntity = clienteRepositoryPort.save(clienteEntity);
        return clienteMapper.toDomain(savedEntity);
    }

    @Override
    public Cliente consultarCliente(String cpf) {
        return clienteRepositoryPort.findByCpf(cpf)
                .map(clienteMapper::toDomain)
                .orElse(null);
    }
}