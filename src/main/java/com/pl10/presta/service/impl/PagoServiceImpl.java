package com.pl10.presta.service.impl;

import com.pl10.presta.converter.PagoConverter;
import com.pl10.presta.entity.Credito;
import com.pl10.presta.entity.Pago;
import com.pl10.presta.enums.CreditoStatus;
import com.pl10.presta.model.PagoModel;
import com.pl10.presta.repository.CreditoRepository;
import com.pl10.presta.repository.PagoRepository;
import com.pl10.presta.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pagoService")
public class PagoServiceImpl implements PagoService {

    @Autowired
    @Qualifier("pagoRepository")
    private PagoRepository pagoRepository;

    @Autowired
    @Qualifier("pagoConverter")
    private PagoConverter pagoConverter;

    @Autowired
    @Qualifier("creditoRepository")
    private CreditoRepository creditoRepository;

    @Override
    public PagoModel savePagoModel(PagoModel pagoModel) {
        Pago pago = pagoConverter.pagoModelToPago(pagoModel);
        pagoModel = pagoConverter.pagoToPagoModel(pagoRepository.save(pago));
        pago = pagoConverter.pagoModelToPago(pagoModel);
        Long cuotasPagas = pagoRepository.sumCuotasByCredito(pago.getCredito());
        if(cuotasPagas.intValue() == pago.getCredito().getCuotas()){
            Credito credito = pago.getCredito();
            credito.setCreditoStatus(CreditoStatus.PAGADO);
            creditoRepository.save(credito);
        }
        return pagoConverter.pagoToPagoModel(pagoRepository.findById(pagoModel.getId()));
    }
}
