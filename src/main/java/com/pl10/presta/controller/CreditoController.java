package com.pl10.presta.controller;

import com.pl10.presta.entity.Credito;
import com.pl10.presta.enums.CreditoStatus;
import com.pl10.presta.enums.CreditoType;
import com.pl10.presta.model.*;
import com.pl10.presta.service.ClienteService;
import com.pl10.presta.service.CreditoService;
import com.pl10.presta.service.PagoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("credito")
public class CreditoController {

    private static final Log LOG = LogFactory.getLog(CreditoController.class);

    @Autowired
    @Qualifier("creditoService")
    private CreditoService creditoService;

    @Autowired
    @Qualifier("clienteService")
    private ClienteService clienteService;

    @Autowired
    @Qualifier("pagoService")
    private PagoService pagoService;

    @GetMapping("/form")
    private ModelAndView formCredito(@RequestParam(name="id", required=false) String id){
        LOG.info("METHOD: formCredito() -- PARAMS: "+id);
        ModelAndView model = new ModelAndView("credito/form");
        CreditoModel creditoModel = new CreditoModel();
        creditoModel.setFecha(new Date());
        creditoModel.setTaza(Double.valueOf(20));
        creditoModel.setCreditoType(CreditoType.DIARIO);
        ClienteModel clienteModel = clienteService.findClienteModelById(id);
        creditoModel.setClienteModel(clienteModel);
        model.addObject("credito",creditoModel);
        LOG.info("RETURN VIEW: credito/form -- Object: "+creditoModel);
        return model;
    }

    @PostMapping("/savecredito")
    private ModelAndView saveCredito(@ModelAttribute(name = "credito") CreditoModel creditoModel){
        LOG.info("METHOD: saveCredito() -- PARAMS: "+creditoModel);
        creditoModel.setValor(creditoModel.getValor() +(creditoModel.getValor() * creditoModel.getTaza()/100));
        creditoModel = creditoService.saveCreditoModel(creditoModel);
        ModelAndView model = new ModelAndView("redirect:/credito/detalle-credito?id="+creditoModel.getId());
        LOG.info("REDIRECT METOD: detalleCredito() -- Object: "+creditoModel.getId());
        return model;
    }

    /**
     *
     * @param id identificador del credito
     * @return la vista de gestión de pagos del credito
     */
    @GetMapping("/detalle-credito")
    private ModelAndView detalleCredito(@RequestParam(name="id", required=false) String id){
        LOG.info("METHOD: detalleCredito() -- PARAMS: "+id);
        ModelAndView model = new ModelAndView("credito/cred-detalle");
        CreditoDetalleModel creditoDetalleModel = creditoService.findByCreditoDetalleModel(id);
        List<CreditoModel> creditoModels = creditoService.allCreditOfClient(creditoDetalleModel.getCreditoModel().getClienteModel());
        PagoModel pagoModel = new PagoModel();
        pagoModel.setCreditoModel(creditoDetalleModel.getCreditoModel());
        model.addObject("creditodetalle",creditoDetalleModel);
        model.addObject("pagoModel",pagoModel);
        model.addObject("creditoModels",creditoModels);
        LOG.info("RETURN VIEW: credito/cred-detalle -- Object: "+creditoDetalleModel+", "+pagoModel);
        return model;
    }


    @PostMapping("/registrar-pago")
    private ModelAndView registrarPago(@ModelAttribute(name = "pagoModel") PagoModel pagoModel){
        LOG.info("METHOD: registrarPago() -- PARAMS: "+pagoModel);
        pagoModel.setCreditoModel(creditoService.findByCreditoModel(pagoModel.getCreditoModel().getId()));
        pagoModel = pagoService.savePagoModel(pagoModel);

        ModelAndView model = new ModelAndView("redirect:/credito/detalle-credito?id="+pagoModel.getCreditoModel().getId());
        LOG.info("REDIRECT METOD: detalleCredito() -- Object: "+pagoModel.getCreditoModel().getId());
        return model;
    }

    @GetMapping("/listaactivos")
    private ModelAndView listaActivos(){
        ModelAndView model = new ModelAndView("credito/activos");
        return model;
    }

    @GetMapping("/listapagados")
    private ModelAndView listaPagados(){
        ModelAndView model = new ModelAndView("credito/pagados");
        return model;
    }

    @GetMapping("/listaanulados")
    private ModelAndView listaAnulados(){
        ModelAndView model = new ModelAndView("credito/inactivos");
        return model;
    }

    @GetMapping("/clientes-credito-a")
    @ResponseBody
    private JsonObject<ClienteModel> allClientesWithCredits(HttpServletRequest request){
        LOG.info("METHOD: allClientesWithCredits() -- PARAMS: "+request);
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart")){
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
        }
        String searchParameter = request.getParameter("sSearch");

        List<ClienteModel> clienteModels = creditoService.searchClienteModels(searchParameter, pageNumber, CreditoStatus.ACTIVO);
        Long countRecords = creditoService.countClienteModels(searchParameter, CreditoStatus.ACTIVO);
        JsonObject<ClienteModel> jsonObject = new JsonObject<ClienteModel>();
        jsonObject.setiTotalDisplayRecords(countRecords.intValue());
        jsonObject.setiTotalRecords(countRecords.intValue());
        jsonObject.setAaData(clienteModels);
        return jsonObject;
    }

    @GetMapping("/clientes-credito-i")
    @ResponseBody
    private JsonObject<ClienteModel> allClientesWithCreditsI(HttpServletRequest request){
        LOG.info("METHOD: allClientesWithCreditsI() -- PARAMS: "+request);
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart")){
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
        }
        String searchParameter = request.getParameter("sSearch");

        List<ClienteModel> clienteModels = creditoService.searchClienteModels(searchParameter, pageNumber, CreditoStatus.ANULADO);
        Long countRecords = creditoService.countClienteModels(searchParameter, CreditoStatus.ANULADO);
        JsonObject<ClienteModel> jsonObject = new JsonObject<ClienteModel>();
        jsonObject.setiTotalDisplayRecords(countRecords.intValue());
        jsonObject.setiTotalRecords(countRecords.intValue());
        jsonObject.setAaData(clienteModels);
        return jsonObject;
    }

    @GetMapping("/clientes-credito-p")
    @ResponseBody
    private JsonObject<ClienteModel> allClientesWithCreditsP(HttpServletRequest request){
        LOG.info("METHOD: allClientesWithCreditsP() -- PARAMS: "+request);
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart")){
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
        }
        String searchParameter = request.getParameter("sSearch");

        List<ClienteModel> clienteModels = creditoService.searchClienteModels(searchParameter, pageNumber, CreditoStatus.PAGADO);
        Long countRecords = creditoService.countClienteModels(searchParameter, CreditoStatus.PAGADO);
        JsonObject<ClienteModel> jsonObject = new JsonObject<ClienteModel>();
        jsonObject.setiTotalDisplayRecords(countRecords.intValue());
        jsonObject.setiTotalRecords(countRecords.intValue());
        jsonObject.setAaData(clienteModels);
        return jsonObject;
    }

    /**
     *
     * @param id identificador del credito
     * @return la vista de gestión de pagos del credito
     */
    @GetMapping("/ver-creditos")
    private ModelAndView creditoCliente(@RequestParam(name="id", required=false) String id){
        LOG.info("METHOD: creditoCliente() -- PARAMS: "+id);
        ModelAndView model = new ModelAndView("credito/creditos-cliente");
        ClienteModel clienteModel = clienteService.findClienteModelById(id);

        List<CreditoModel> creditoModels = creditoService.allCreditOfClient(clienteModel);
        model.addObject("creditoModels",creditoModels);
        model.addObject("clienteModel",clienteModel);
        LOG.info("RETURN VIEW: credito/creditos-cliente -- Object: "+creditoModels+" "+clienteModel);
        return model;
    }

    @GetMapping("/anular-credito")
    private ModelAndView anularCredito(@RequestParam(name="id", required=false) String id){
        LOG.info("METHOD: anularCredito() -- PARAMS: "+id);
        CreditoModel creditoModel = creditoService.findByCreditoModel(id);
        creditoModel.setCreditoStatus(CreditoStatus.ANULADO);
        System.out.println(creditoModel.getCreditoStatus());
        creditoModel = creditoService.saveCreditoModel(creditoModel);
        return new ModelAndView("redirect:/credito/ver-creditos?id="+creditoModel.getClienteModel().getId());
    }
}
