package com.pl10.presta.service.impl;

import com.pl10.presta.converter.ClienteConverter;
import com.pl10.presta.converter.CreditoConverter;
import com.pl10.presta.converter.PagoConverter;
import com.pl10.presta.entity.Cliente;
import com.pl10.presta.entity.Credito;
import com.pl10.presta.entity.Pago;
import com.pl10.presta.enums.CreditoStatus;
import com.pl10.presta.enums.CreditoType;
import com.pl10.presta.model.ClienteModel;
import com.pl10.presta.model.CreditoDetalleModel;
import com.pl10.presta.model.CreditoModel;
import com.pl10.presta.model.PagoModel;
import com.pl10.presta.repository.CreditoRepository;
import com.pl10.presta.repository.PagoRepository;
import com.pl10.presta.repository.query.CreditoDslRepository;
import com.pl10.presta.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("creditoService")
public class CreditoServiceImpl implements CreditoService {

    @Autowired
    @Qualifier("creditoRepository")
    private CreditoRepository creditoRepository;

    @Autowired
    @Qualifier("pagoRepository")
    private PagoRepository pagoRepository;

    @Autowired
    @Qualifier("creditoConverter")
    private CreditoConverter creditoConverter;

    @Autowired
    @Qualifier("pagoConverter")
    private PagoConverter pagoConverter;

    @Autowired
    @Qualifier("clienteConverter")
    private ClienteConverter clienteConverter;

    @Autowired
    @Qualifier("creditoDslRepository")
    private CreditoDslRepository creditoDslRepository;

    @Override
    public CreditoModel saveCreditoModel(CreditoModel creditoModel) {
        if(creditoModel.getCreditoStatus()!=CreditoStatus.ANULADO){
            creditoModel.setCreditoStatus(CreditoStatus.ACTIVO);
        }
        Credito credito = creditoConverter.creditoModelToCredito(creditoModel);
        creditoModel = creditoConverter.creditoToCreditoModel(creditoRepository.save(credito));
        //generamos primer pago.
        PagoModel pagoModel = new PagoModel();
        pagoModel.setCreditoModel(creditoModel);
        pagoModel.setCuotas(1);
        pagoModel.setFecha(creditoModel.getFecha());
        Double valorCuota = creditoModel.getValor() / creditoModel.getCuotas();
        pagoModel.setValor(valorCuota);
        Pago pago = pagoConverter.pagoModelToPago(pagoModel);
        return pagoConverter.pagoToPagoModel(pagoRepository.save(pago)).getCreditoModel();
    }

    @Override
    public CreditoModel findByCreditoModel(String id) {
        return creditoConverter.creditoToCreditoModel(findCreditoById(id));
    }

    @Override
    public Credito findCreditoById(String id) {
        return creditoRepository.findById(id);
    }

    @Override
    public CreditoDetalleModel findByCreditoDetalleModel(String id) {
        CreditoModel creditoModel = findByCreditoModel(id);
        List<Pago> pagos = pagoRepository.findAllByCredito(creditoConverter.creditoModelToCredito(creditoModel));
        List<PagoModel> pagoModels = new ArrayList<PagoModel>();
        Double valorPagado = 0.0;
        Integer cuotasPagadas = 0;
        for (Pago pago : pagos) {
            pagoModels.add(pagoConverter.pagoToPagoModel(pago));
            valorPagado += pago.getValor();
            cuotasPagadas += pago.getCuotas();
        }
        CreditoDetalleModel creditoDetalleModel = new CreditoDetalleModel();
        creditoDetalleModel.setCreditoModel(creditoModel);
        creditoDetalleModel.setPagoModels(pagoModels);
        creditoDetalleModel.setValorcuota(creditoModel.getValor() / creditoModel.getCuotas());
        creditoDetalleModel.setFaltante(creditoModel.getValor() - valorPagado);
        creditoDetalleModel.setFaltante100((creditoDetalleModel.getFaltante() * 100) / creditoModel.getValor());
        creditoDetalleModel.setCuotasfaltantes(creditoModel.getCuotas() - cuotasPagadas);
        Date fechaCuota = calcularFechaDeCuota(creditoModel.getFecha(), creditoModel.getCreditoType(), cuotasPagadas);
        creditoDetalleModel.setProximacuota(fechaCuota);
        return creditoDetalleModel;
    }

    @Override
    public List<CreditoModel> allCreditOfClient(ClienteModel clienteModel) {
        Cliente cliente = clienteConverter.clienteModelToCliente(clienteModel);
        List<Credito> creditos = creditoRepository.findAllByCliente(cliente);
        List<CreditoModel> creditoModels = new ArrayList<CreditoModel>();
        for(Credito credito : creditos){
            creditoModels.add(creditoConverter.creditoToCreditoModel(credito));
        }
        return creditoModels;
    }

    @Override
    public List<ClienteModel> searchClienteModels(String str, int page, CreditoStatus creditoStatus) {
        List<Cliente> clientes = creditoDslRepository.searchAllClienteCredit(str, page, creditoStatus);
        List<ClienteModel> clienteModels = new ArrayList<ClienteModel>();
        for(Cliente cliente : clientes){
            clienteModels.add(clienteConverter.clienteToClienteModel(cliente));
        }
        return clienteModels;
    }

    @Override
    public Long countClienteModels(String str, CreditoStatus creditoStatus) {
        return creditoDslRepository.countAllClienteCredit(str, creditoStatus);
    }

    private Date calcularFechaDeCuota(Date fecha, CreditoType creditoType, Integer cuotasPagadas) {
        for (int i = 0; i < cuotasPagadas; i++) {
            if (creditoType.equals(CreditoType.DIARIO)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(fecha);
                cal.add(Calendar.DATE, 1);
                fecha = cal.getTime();
            } else if (creditoType.equals(CreditoType.SEMANAL)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(fecha);
                cal.add(Calendar.DATE, 7);
                fecha = cal.getTime();
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(fecha);
                cal.add(Calendar.MONTH, 1);
                fecha = cal.getTime();
            }
        }
        return fecha;
    }
}
