package it.prova.gestionalepizzeria.service;

import it.prova.gestionalepizzeria.model.Pizza;

import java.util.List;

public interface PizzaService {

    List<Pizza> listAll();

    List<Pizza> listAllAttive();

    List<Pizza> findByExample(Pizza example);

    Pizza caricaSingolo(Long id);

    void inserisciNuovo(Pizza pizza);

    void aggiorna(Pizza pizza);

    void disattiva(Long id);
}
