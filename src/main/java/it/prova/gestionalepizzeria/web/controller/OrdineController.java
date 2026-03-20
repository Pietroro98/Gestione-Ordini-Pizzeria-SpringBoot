package it.prova.gestionalepizzeria.web.controller;

import it.prova.gestionalepizzeria.dto.ClienteDTO;
import it.prova.gestionalepizzeria.dto.OrdineDTO;
import it.prova.gestionalepizzeria.dto.PizzaDTO;
import it.prova.gestionalepizzeria.service.ClienteService;
import it.prova.gestionalepizzeria.service.OrdineService;
import it.prova.gestionalepizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pizzeria/ordine")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("pizza_list_attribute", PizzaDTO.createListFromModelList(pizzaService.listAllAttive()));
        model.addAttribute("cliente_list_attribute", ClienteDTO.createListFromModelList(clienteService.listAll()));
        model.addAttribute("insert_ordine_attr", new OrdineDTO());
        return "ordine/insert";
    }

}
