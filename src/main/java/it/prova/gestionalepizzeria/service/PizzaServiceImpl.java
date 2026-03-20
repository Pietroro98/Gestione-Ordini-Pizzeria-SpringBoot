package it.prova.gestionalepizzeria.service;

import it.prova.gestionalepizzeria.model.Pizza;
import it.prova.gestionalepizzeria.repository.pizza.PizzaRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> listAll() {
        return pizzaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pizza> listAllAttive() {
        return pizzaRepository.findByAttivoTrueOrderByDescrizioneAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pizza> findByExample(Pizza example) {
        Specification<Pizza> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(example.getDescrizione())) {
                predicates.add(cb.like(cb.upper(root.get("descrizione")),
                        "%" + example.getDescrizione().toUpperCase() + "%"));
            }
            if (StringUtils.isNotBlank(example.getIngredienti())) {
                predicates.add(cb.like(cb.upper(root.get("ingredienti")),
                        "%" + example.getIngredienti().toUpperCase() + "%"));
            }
            if (example.getPrezzoBase() != null) {
                predicates.add(cb.equal(root.get("prezzoBase"), example.getPrezzoBase()));
            }
            if (example.getAttivo() != null) {
                predicates.add(cb.equal(root.get("attivo"), example.getAttivo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return pizzaRepository.findAll(specification);
    }

    @Override
    public Pizza caricaSingolo(Long id) {
        return null;
    }

    @Override
    public void inserisciNuovo(Pizza pizza) {

    }

    @Override
    public void aggiorna(Pizza pizza) {

    }

    @Override
    public void disattiva(Long id) {

    }
}
