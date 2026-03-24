package it.prova.gestionalepizzeria.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.prova.gestionalepizzeria.dto.ClienteDTO;
import it.prova.gestionalepizzeria.model.Cliente;
import it.prova.gestionalepizzeria.model.ClienteProjectionInterface;
import it.prova.gestionalepizzeria.repository.cliente.ClienteRepository;
import it.prova.gestionalepizzeria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pizzeria/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

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

    /*@GetMapping(value = "/searchAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody String searchCliente(@RequestParam String term) {

        List<Cliente> listaClientiByTerm = clienteService.searchAttiviByTerm(term);
        return buildJsonResponse(listaClientiByTerm);
    }

    private String buildJsonResponse(List<Cliente> listaClienti) {
        JsonArray ja = new JsonArray();

        for (Cliente clienteItem : listaClienti) {
            JsonObject jo = new JsonObject();
            jo.addProperty("value", clienteItem.getId());
            jo.addProperty("label", clienteItem.getNome() + " " + clienteItem.getCognome());
            ja.add(jo);
        }

        return new Gson().toJson(ja);
    }*/

    @GetMapping(value = "/searchAjax",  produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody String searchAjax(@RequestParam String term) {
        List<ClienteProjectionInterface> listaClienti = clienteService.searchAttiviWithPromoByTerm(term);

        JsonArray jsonArray = new JsonArray();

        for (ClienteProjectionInterface clienteItem : listaClienti) {
            JsonObject jo = new JsonObject();
            jo.addProperty("value", clienteItem.getValue());
            jo.addProperty("label", clienteItem.getLabel());
            jo.addProperty("livelloPromo", clienteItem.getLivelloPromo());
            jsonArray.add(jo);
        }

        return new Gson().toJson(jsonArray);
    }
}
