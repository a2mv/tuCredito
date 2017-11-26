package com.pl10.presta.controller;

import com.pl10.presta.enums.CreditoStatus;
import com.pl10.presta.model.ClienteModel;
import com.pl10.presta.service.ClienteService;
import com.pl10.presta.service.CreditoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("estadistic")
public class EstadisticaController {

    private static final Log LOG = LogFactory.getLog(EstadisticaController.class);

    @Autowired
    @Qualifier("creditoService")
    private CreditoService creditoService;

    @Autowired
    @Qualifier("clienteService")
    private ClienteService clienteService;

    @GetMapping("/creditos-activos")
    @ResponseBody
    private Long creditosActivos(@RequestParam(name="id", required=false) String id){
        LOG.info("METHOD: creditosActivos() -- PARAMS: "+id);
        ClienteModel clienteModel = clienteService.findClienteModelById(id);
        Long creditos = creditoService.countCreditosByClient(clienteModel, CreditoStatus.ACTIVO);
        return creditos;
    }

    @GetMapping("/creditos-pagados")
    @ResponseBody
    private Long creditosPagados(@RequestParam(name="id", required=false) String id){
        LOG.info("METHOD: creditosActivos() -- PARAMS: "+id);
        ClienteModel clienteModel = clienteService.findClienteModelById(id);
        Long creditos = creditoService.countCreditosByClient(clienteModel, CreditoStatus.PAGADO);
        return creditos;
    }
}
