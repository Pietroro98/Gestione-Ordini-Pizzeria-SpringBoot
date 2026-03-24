package it.prova.gestionalepizzeria.web.controller;
import it.prova.gestionalepizzeria.dto.ClienteDTO;
import it.prova.gestionalepizzeria.model.ClienteProjectionInterface;
import it.prova.gestionalepizzeria.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/pizzeria/statistiche")
public class StatisticheController {

    @Autowired
    private OrdineService ordineService;


    @GetMapping("/show")
    public String show() {
        return "statistiche/show";
    }

    @PostMapping("/report")
    public String report(@RequestParam("dataDa") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDa,
                         @RequestParam("dataA") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataA,
                         Model model) {

        model.addAttribute("dataDa", dataDa);
        model.addAttribute("dataA", dataA);

        if (dataDa == null || dataA == null) {
            model.addAttribute("errorMessage", "Attenzione! Data inizio e data fine sono obbligatorie.");
            return "statistiche/show";
        }

        if (dataDa.isAfter(dataA)) {
            model.addAttribute("errorMessage", "Attenzione! Data inizio non puo essere maggiore di data fine!");
            return "statistiche/show";
        }

        LocalDateTime dataInizio = dataDa.atStartOfDay();
        LocalDateTime dataFine = dataA.atTime(LocalTime.MAX);

        model.addAttribute("ricaviTotali", ordineService.sommaRicaviTraDate(dataInizio, dataFine));
        model.addAttribute("costiTotali", ordineService.sommaCostiTraDate(dataInizio, dataFine));
        model.addAttribute("numeroOrdiniTotali", ordineService.contaOrdiniTraDate(dataInizio, dataFine));
        model.addAttribute("numeroPizzeTotali", ordineService.contaPizzeOrdinateTraDate(dataInizio, dataFine));
        model.addAttribute("clientiVirtuosi",ClienteDTO.createListFromModelList(ordineService.cercaClientiVirtuosi(dataInizio, dataFine)));

        model.addAttribute("clientiSilver", ordineService.cercaClientiSilver(dataInizio, dataFine));
        model.addAttribute("clientiGold", ordineService.cercaClientiGold(dataInizio, dataFine));

        return "statistiche/show";
    }
}
