package com.pl10.presta.converter;

import com.pl10.presta.entity.Pago;
import com.pl10.presta.model.PagoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pagoConverter")
public class PagoConverter {

    @Autowired
    @Qualifier("creditoConverter")
    private CreditoConverter creditoConverter;

    public Pago pagoModelToPago(PagoModel pagoModel){
        Pago pago = new Pago();
        pago.setCredito(creditoConverter.creditoModelToCredito(pagoModel.getCreditoModel()));
        pago.setCuotas(pagoModel.getCuotas());
        pago.setFecha(pagoModel.getFecha());
        pago.setId(pagoModel.getId());
        pago.setValor(pagoModel.getValor());
        return pago;
    }

    public PagoModel pagoToPagoModel(Pago pago){
        PagoModel pagoModel = new PagoModel();
        pagoModel.setCreditoModel(creditoConverter.creditoToCreditoModel(pago.getCredito()));
        pagoModel.setCuotas(pago.getCuotas());
        pagoModel.setFecha(pago.getFecha());
        pagoModel.setId(pago.getId());
        pagoModel.setValor(pago.getValor());
        return pagoModel;
    }
}
