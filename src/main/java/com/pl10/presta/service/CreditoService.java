package com.pl10.presta.service;

import com.pl10.presta.entity.Cliente;
import com.pl10.presta.entity.Credito;
import com.pl10.presta.enums.CreditoStatus;
import com.pl10.presta.model.ClienteModel;
import com.pl10.presta.model.CreditoDetalleModel;
import com.pl10.presta.model.CreditoModel;

import java.util.List;

public interface CreditoService {

    public abstract CreditoModel saveCreditoModel(CreditoModel creditoModel);

    public abstract CreditoModel findByCreditoModel(String id);

    public abstract Credito findCreditoById(String id);

    public abstract CreditoDetalleModel findByCreditoDetalleModel(String id);

    public abstract List<CreditoModel> allCreditOfClient(ClienteModel clienteModel);

    public abstract List<ClienteModel> searchClienteModels(String str, int page, CreditoStatus creditoStatus);

    public abstract Long countClienteModels(String str, CreditoStatus creditoStatus);

}
