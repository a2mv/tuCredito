package com.pl10.presta.converter;

import com.pl10.presta.entity.Cliente;
import com.pl10.presta.model.ClienteModel;
import org.springframework.stereotype.Component;

@Component("clienteConverter")
public class ClienteConverter {

    public Cliente clienteModelToCliente(ClienteModel clienteModel){
        Cliente cliente = new Cliente();
        cliente.setDireccion(clienteModel.getDireccion());
        cliente.setFullname(clienteModel.getFullname());
        cliente.setId(clienteModel.getId());
        cliente.setNdocumento(clienteModel.getNdocumento());
        cliente.setObservacion(clienteModel.getObservacion());
        cliente.setTelefono(clienteModel.getTelefono());
        return cliente;
    }

    public ClienteModel clienteToClienteModel(Cliente cliente){
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setDireccion(cliente.getDireccion());
        clienteModel.setFullname(cliente.getFullname());
        clienteModel.setId(cliente.getId());
        clienteModel.setNdocumento(cliente.getNdocumento());
        clienteModel.setObservacion(cliente.getObservacion());
        clienteModel.setTelefono(cliente.getTelefono());
        return clienteModel;
    }
}
