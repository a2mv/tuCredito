package com.pl10.presta.controller;

import com.pl10.presta.entity.Cliente;
import com.pl10.presta.model.ClienteModel;
import com.pl10.presta.model.JsonObject;
import com.pl10.presta.service.ClienteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    private static final Log LOG = LogFactory.getLog(ClienteController.class);

    @Autowired
    @Qualifier("clienteService")
    ClienteService clienteService;

    @GetMapping("/form")
    private ModelAndView formCliente(@RequestParam(name="id", required=false) String id){
        LOG.info("METHOD: formCliente() -- PARAMS: "+id);
        ModelAndView model = new ModelAndView("cliente/form");
        ClienteModel clienteModel = new ClienteModel();
        if(id == null | !id.equals("0")){
            clienteModel = clienteService.findClienteModelById(id);
        }
        model.addObject("cliente",clienteModel);
        LOG.info("RETURN VIEW: cliente/form -- Object: "+clienteModel);
        return model;
    }

    @PostMapping("/savecliente")
    private ModelAndView saveCliente(@ModelAttribute(name = "cliente") ClienteModel clienteModel){
        LOG.info("METHOD: saveCliente() -- PARAMS: "+clienteModel);
        clienteModel = clienteService.saveClienteModel(clienteModel);
        ModelAndView model = new ModelAndView("cliente/form");
        model.addObject("cliente",clienteModel);
        model.addObject("message","Datos de cliente guardados con éxito");
        model.addObject("type", "success");
        LOG.info("RETURN VIEW: cliente/form -- Object: "+clienteModel);
        return model;
    }

    @GetMapping("/lista")
    private ModelAndView listaClientes(){
        ModelAndView model = new ModelAndView("cliente/index");

        return model;
    }

    @GetMapping("/allclientes")
    @ResponseBody
    private JsonObject<ClienteModel> allClientes(HttpServletRequest request){
        LOG.info("METHOD: allCliente() -- PARAMS: "+request);
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart")){
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
        }
        String searchParameter = request.getParameter("sSearch");
        List<ClienteModel> clienteModels = clienteService.searchClienteModels(searchParameter, pageNumber);
        Long countRecords = clienteService.countClienteModels(searchParameter);
        JsonObject<ClienteModel> jsonObject = new JsonObject<ClienteModel>();
        jsonObject.setiTotalDisplayRecords(countRecords.intValue());
        jsonObject.setiTotalRecords(countRecords.intValue());
        jsonObject.setAaData(clienteModels);
        return jsonObject;
    }
}
