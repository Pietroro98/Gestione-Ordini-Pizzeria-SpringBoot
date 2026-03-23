package it.prova.gestionalepizzeria.service;

import it.prova.gestionalepizzeria.model.Cliente;
import it.prova.gestionalepizzeria.model.Ordine;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdineService {
    List<Ordine> listAll();

    List<Ordine> findByExample(Ordine example);

    Ordine caricaSingolo(Long id);

    Ordine caricaSingoloEager(Long id);

    void inserisciNuovo(Ordine ordine);

    void aggiorna(Ordine ordine);

    void rimuovi(Long id);

    Float calcolaPrezzoOrdine(Ordine ordine);

    Float sommaRicaviTraDate(LocalDateTime dataInizio, LocalDateTime dataFine);

    Float sommaCostiTraDate(LocalDateTime dataInizio, LocalDateTime dataFine);

    Integer contaOrdiniTraDate(LocalDateTime dataInizio, LocalDateTime dataFine);

    Integer contaPizzeOrdinateTraDate(LocalDateTime dataInizio, LocalDateTime dataFine);

    List<Cliente> cercaClientiVirtuosi(LocalDateTime dataInizio, LocalDateTime dataFine);
}
