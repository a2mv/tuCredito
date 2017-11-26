package com.pl10.presta.converter;

import com.pl10.presta.entity.Credito;
import com.pl10.presta.model.CreditoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("creditoConverter")
public class CreditoConverter {

    @Autowired
    @Qualifier("clienteConverter")
    private ClienteConverter clienteConverter;

    public Credito creditoModelToCredito(CreditoModel creditoModel){
        Credito credito = new Credito();
        credito.setCliente(clienteConverter.clienteModelToCliente(creditoModel.getClienteModel()));
        credito.setCuotas(creditoModel.getCuotas());
        credito.setFecha(creditoModel.getFecha());
        credito.setId(creditoModel.getId());
        credito.setTaza(creditoModel.getTaza());
        credito.setCreditoType(creditoModel.getCreditoType());
        credito.setValor(creditoModel.getValor());
        credito.setCreditoStatus(creditoModel.getCreditoStatus());
        return credito;
    }

    public CreditoModel creditoToCreditoModel(Credito credito){
        CreditoModel creditoModel = new CreditoModel();
        creditoModel.setClienteModel(clienteConverter.clienteToClienteModel(credito.getCliente()));
        creditoModel.setCuotas(credito.getCuotas());
        creditoModel.setFecha(credito.getFecha());
        creditoModel.setId(credito.getId());
        creditoModel.setTaza(credito.getTaza());
        creditoModel.setCreditoType(credito.getCreditoType());
        creditoModel.setValor(credito.getValor());
        creditoModel.setCreditoStatus(credito.getCreditoStatus());
        return creditoModel;
    }
}
