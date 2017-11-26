package com.pl10.presta.service;

import com.pl10.presta.entity.Cliente;
import com.pl10.presta.model.ClienteModel;

import java.util.List;

public interface ClienteService {

    public abstract ClienteModel saveClienteModel(ClienteModel clienteModel);

    public abstract ClienteModel findClienteModelById(String id);

    public abstract Cliente findClienteById(String id);

    public abstract List<ClienteModel> searchClienteModels(String str, int page);

    public abstract Long countClienteModels(String str);


}
