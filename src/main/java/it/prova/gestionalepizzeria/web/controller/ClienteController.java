package it.prova.gestionalepizzeria.web.controller;

import it.prova.gestionalepizzeria.dto.ClienteDTO;
import it.prova.gestionalepizzeria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pizzeria/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

   @GetMapping({"", "/list"})
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("cliente/list");
        mv.addObject("cliente_list_attribute", ClienteDTO.createListFromModelList(clienteService.listAll()));
        return mv;
    }

    @GetMapping("/search")
    public String search() {
        return "cliente/search";
    }

    @PostMapping("/list")
    public String searchResults(ClienteDTO example, Model model) {
        model.addAttribute("cliente_list_attribute",
                ClienteDTO.createListFromModelList(clienteService.findByExample(example.buildModel())));
        return "cliente/list";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("insert_cliente_attr", new ClienteDTO());
        return "cliente/insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("insert_cliente_attr") ClienteDTO dto, BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cliente/insert";
        }
        clienteService.inserisciNuovo(dto.buildModel());
        redirectAttributes.addFlashAttribute("successMessage", "Cliente inserito correttamente");
        return "redirect:/pizzeria/clienti";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("show_cliente_attr", ClienteDTO.buildFromModel(clienteService.caricaSingoloElemento(id)));
        return "cliente/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("edit_cliente_attr", ClienteDTO.buildFromModel(clienteService.caricaSingoloElemento(id)));
        return "cliente/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("edit_cliente_attr") ClienteDTO dto, BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cliente/edit";
        }
        clienteService.aggiorna(dto.buildModel());
        redirectAttributes.addFlashAttribute("successMessage", "Cliente aggiornato correttamente");
        return "redirect:/pizzeria/clienti";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clienteService.disattiva(id);
        redirectAttributes.addFlashAttribute("successMessage", "Cliente disattivato correttamente");
        return "redirect:/pizzeria/clienti";
    }

    @PostMapping("/attiva/{id}")
    public String attiva(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clienteService.attiva(id);
        redirectAttributes.addFlashAttribute("successMessage", "Cliente attivato correttamente!");
        return "redirect:/pizzeria/clienti";
    }

    @PostMapping("/disattiva/{id}")
    public String disattiva(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clienteService.disattiva(id);
        redirectAttributes.addFlashAttribute("successMessage", "Cliente disattivato correttamente!");
        return "redirect:/pizzeria/clienti";
    }
}
