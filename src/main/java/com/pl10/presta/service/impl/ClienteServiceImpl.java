package com.pl10.presta.service.impl;

import com.pl10.presta.converter.ClienteConverter;
import com.pl10.presta.entity.Cliente;
import com.pl10.presta.model.ClienteModel;
import com.pl10.presta.repository.ClienteRepository;
import com.pl10.presta.repository.query.ClienteDslRepository;
import com.pl10.presta.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

    @Autowired
    @Qualifier("clienteConverter")
    private ClienteConverter clienteConverter;

    @Autowired
    @Qualifier("clienteDslRepository")
    private ClienteDslRepository clienteDslRepository;

    @Override
    public ClienteModel saveClienteModel(ClienteModel clienteModel) {
        Cliente cliente = clienteConverter.clienteModelToCliente(clienteModel);
        return clienteConverter.clienteToClienteModel(clienteRepository.save(cliente));
    }

    @Override
    public ClienteModel findClienteModelById(String id) {
        return clienteConverter.clienteToClienteModel(findClienteById(id));
    }

    @Override
    public Cliente findClienteById(String id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<ClienteModel> searchClienteModels(String str, int page) {
        List<Cliente> clientes = clienteDslRepository.searchAllDataCliente(str, page);
        List<ClienteModel> clienteModels = new ArrayList<ClienteModel>();
        for(Cliente cliente : clientes){
            clienteModels.add(clienteConverter.clienteToClienteModel(cliente));
        }
        return clienteModels;
    }

    @Override
    public Long countClienteModels(String str) {
        return clienteDslRepository.countAllDataCliente(str);
    }
}
