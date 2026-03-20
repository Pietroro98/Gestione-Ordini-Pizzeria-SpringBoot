package it.prova.gestionalepizzeria.web.controller;

import it.prova.gestionalepizzeria.dto.PizzaDTO;
import it.prova.gestionalepizzeria.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pizzeria/pizze")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping({"", "/list"})
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("pizza/list");
        mv.addObject("pizza_list_attribute", PizzaDTO.createListFromModelList(pizzaService.listAll()));
        return mv;
    }

    @GetMapping("/search")
    public String search() {
        return "pizza/search";
    }

    @PostMapping("/list")
    public String searchResults(PizzaDTO example, Model model) {
        model.addAttribute("pizza_list_attribute",
                PizzaDTO.createListFromModelList(pizzaService.findByExample(example.buildModel())));
        return "pizza/list";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("insert_pizza_attr", new PizzaDTO());
        return "pizza/insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("insert_pizza_attr") PizzaDTO dto, BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "pizza/insert";
        }
        pizzaService.inserisciNuovo(dto.buildModel());
        redirectAttributes.addFlashAttribute("successMessage", "Pizza inserita correttamente");
        return "redirect:/pizzeria/pizze";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("show_pizza_attr", PizzaDTO.BuildFromModel(pizzaService.caricaSingolo(id)));
        return "pizza/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("edit_pizza_attr", PizzaDTO.BuildFromModel(pizzaService.caricaSingolo(id)));
        return "pizza/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("edit_pizza_attr") PizzaDTO dto, BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "pizza/edit";
        }
        pizzaService.aggiorna(dto.buildModel());
        redirectAttributes.addFlashAttribute("successMessage", "Pizza aggiornata correttamente");
        return "redirect:/pizzeria/pizze";
    }

    @PostMapping("/attiva/{id}")
    public String attiva(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pizzaService.attiva(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza attivata correttamente");
        return "redirect:/pizzeria/pizze";
    }

    @PostMapping("/disattiva/{id}")
    public String disattiva(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pizzaService.disattiva(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza disattivata correttamente");
        return "redirect:/pizzeria/pizze";
    }

}
