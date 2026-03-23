package it.prova.gestionalepizzeria.web.controller;

import it.prova.gestionalepizzeria.dto.ClienteDTO;
import it.prova.gestionalepizzeria.dto.OrdineDTO;
import it.prova.gestionalepizzeria.dto.PizzaDTO;
import it.prova.gestionalepizzeria.model.Cliente;
import it.prova.gestionalepizzeria.model.Ordine;
import it.prova.gestionalepizzeria.model.Pizza;
import it.prova.gestionalepizzeria.service.ClienteService;
import it.prova.gestionalepizzeria.service.OrdineService;
import it.prova.gestionalepizzeria.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller
@RequestMapping("/pizzeria/ordine")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping({"", "/list"})
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("ordine/list");
        mv.addObject("ordine_list_attribute", OrdineDTO.createListFromModelList(ordineService.listAll(), true));
        return mv;
    }

    @GetMapping("/search")
    public String search() {
        return "ordine/search";
    }

    @PostMapping("/list")
    public String searchResults(OrdineDTO example, Model model) {
        model.addAttribute("ordine_list_attribute",
                OrdineDTO.createListFromModelList(ordineService.findByExample(example.buildModel()), true));
        return "ordine/list";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        populateFormLists(model);
        OrdineDTO ordineDTO = new OrdineDTO();
        ordineDTO.setClosed(Boolean.FALSE);
        model.addAttribute("insert_ordine_attr", ordineDTO);
        return "ordine/insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("insert_ordine_attr") OrdineDTO dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        int numeroPizzaRichieste = dto.getPizzaIds() != null ? dto.getPizzaIds().size() : 0;
        Cliente cliente = dto.getCliente() != null && dto.getCliente().getId() != null
                ? clienteService.caricaSingoloElemento(dto.getCliente().getId())
                : null;

        if (cliente == null) {
            result.rejectValue("cliente", "cliente.notFound", "Il cliente selezionato non è valido");
        }

        Set<Pizza> pizzeSelezionate = new LinkedHashSet<>();
        if (dto.getPizzaIds() != null) {
            for (Long pizzaId : dto.getPizzaIds()) {
                Pizza pizza = pizzaService.caricaSingolo(pizzaId);
                if (pizza != null) {
                    pizzeSelezionate.add(pizza);
                }
            }
        }

        if (pizzeSelezionate.size() != numeroPizzaRichieste) {
            result.rejectValue("pizzaIds", "pizzaIds.invalid", "Una o più pizze selezionate non sono valide");
        }

        if (result.hasErrors()) {
            populateFormLists(model);
            return "ordine/insert";
        }

        Ordine ordineDaInserire = dto.buildModel();
        ordineDaInserire.setCliente(cliente);
        ordineDaInserire.setPizze(pizzeSelezionate);
        ordineDaInserire.setClosed(dto.getClosed() == null ? Boolean.FALSE : dto.getClosed());
        ordineDaInserire.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordineDaInserire));
        ordineService.inserisciNuovo(ordineDaInserire);
        redirectAttributes.addFlashAttribute("successMessage", "Ordine inserito correttamente");
        return "redirect:/pizzeria/ordine";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("show_ordine_attr", OrdineDTO.buildFromModel(ordineService.caricaSingoloEager(id), true));
        return "ordine/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        populateFormLists(model);
        model.addAttribute("edit_ordine_attr", OrdineDTO.buildFromModel(ordineService.caricaSingoloEager(id), true));
        return "ordine/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("edit_ordine_attr") OrdineDTO dto, BindingResult result, Model model,
                         RedirectAttributes redirectAttributes) {

        int numeroPizzaRichieste = dto.getPizzaIds() != null ? dto.getPizzaIds().size() : 0;
        Cliente cliente = dto.getCliente() != null && dto.getCliente().getId() != null
                ? clienteService.caricaSingoloElemento(dto.getCliente().getId())
                : null;

        if (cliente == null) {
            result.rejectValue("cliente", "cliente.notFound", "Il cliente selezionato non è valido");
        }

        Set<Pizza> pizzeSelezionate = new LinkedHashSet<>();
        if (dto.getPizzaIds() != null) {
            for (Long pizzaId : dto.getPizzaIds()) {
                Pizza pizza = pizzaService.caricaSingolo(pizzaId);
                if (pizza != null) {
                    pizzeSelezionate.add(pizza);
                }
            }
        }

        if (pizzeSelezionate.size() != numeroPizzaRichieste) {
            result.rejectValue("pizzaIds", "pizzaIds.invalid", "Una o più pizze selezionate non sono valide");
        }

        if (result.hasErrors()) {
            populateFormLists(model);
            return "ordine/edit";
        }

        Ordine ordineDaAggiornare = dto.buildModel();
        ordineDaAggiornare.setCliente(cliente);
        ordineDaAggiornare.setPizze(pizzeSelezionate);
        ordineDaAggiornare.setClosed(dto.getClosed() == null ? Boolean.FALSE : dto.getClosed());
        ordineDaAggiornare.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordineDaAggiornare));
        ordineService.aggiorna(ordineDaAggiornare);
        redirectAttributes.addFlashAttribute("successMessage", "Ordine aggiornato correttamente");
        return "redirect:/pizzeria/ordine";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ordineService.rimuovi(id);
        redirectAttributes.addFlashAttribute("successMessage", "Ordine eliminato correttamente");
        return "redirect:/pizzeria/ordine";
    }

    @PostMapping("/closed/{id}")
    public String closeOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Ordine ordineDaChiudere = ordineService.caricaSingoloEager(id);
        if (ordineDaChiudere != null) {
            ordineDaChiudere.setClosed(Boolean.TRUE);
            ordineService.aggiorna(ordineDaChiudere);
            redirectAttributes.addFlashAttribute("successMessage", "Ordine chiuso correttamente");
        }
        return "redirect:/pizzeria/ordine";
    }

    private void populateFormLists(Model model) {
        model.addAttribute("pizza_list_attribute", PizzaDTO.createListFromModelList(pizzaService.listAllAttive()));
        model.addAttribute("cliente_list_attribute", ClienteDTO.createListFromModelList(clienteService.listAll()));
    }

}
