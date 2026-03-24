package it.prova.gestionalepizzeria.service;

import it.prova.gestionalepizzeria.model.Cliente;
import it.prova.gestionalepizzeria.model.Ordine;
import it.prova.gestionalepizzeria.model.Pizza;
import it.prova.gestionalepizzeria.repository.ordine.OrdineRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;


    @Override
    public List<Ordine> listAll() {
        return ordineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ordine> findByExample(Ordine example) {
        Specification<Ordine> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(example.getCodice())) {
                predicates.add(cb.like(cb.upper(root.get("codice")), "%" + example.getCodice().toUpperCase() + "%"));
            }
            if (example.getDataOrdine() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataOrdine"), example.getDataOrdine()));
            }
            if (example.getClosed() != null) {
                predicates.add(cb.equal(root.get("closed"), example.getClosed()));
            }
            if (example.getCliente() != null && example.getCliente().getId() != null) {
                predicates.add(cb.equal(root.get("cliente").get("id"), example.getCliente().getId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return ordineRepository.findAll(specification);
    }

    @Override
    @Transactional(readOnly = true)
    public Ordine caricaSingolo(Long id) {
        return ordineRepository.findById(id).orElse(null);
    }

    @Override
    public Ordine caricaSingoloEager(Long id) {
        return ordineRepository.findSingleOrdineEager(id);
    }

    @Override
    public void inserisciNuovo(Ordine ordine) {
        ordineRepository.save(ordine);
    }

    @Override
    public void aggiorna(Ordine ordine) {
        ordineRepository.save(ordine);
    }

    @Override
    @Transactional
    public void rimuovi(Long id) {
        ordineRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Float calcolaPrezzoOrdine(Ordine ordine) {
        if (ordine == null || ordine.getPizze() == null) {
            return 0F;
        }

        float totalBase = 0F;
        for (Pizza pizza : ordine.getPizze()) {
            if (pizza.getPrezzoBase() != null) {
                totalBase += pizza.getPrezzoBase();
            }
        }
        float totaleVendita = totalBase + (totalBase * 20 / 100);

        float percentualeSconto = calcolaPercentualeSconto(ordine);
        float importoSconto = totaleVendita * percentualeSconto / 100;
        return totaleVendita - importoSconto;
    }

    private float calcolaPercentualeSconto(Ordine ordine)
    {
        Long clienteDaVerificare = ordine.getCliente().getId();
        if ( clienteDaVerificare == null ) {
            return 0F;
        }

        int numeroOrdini = (int) ordine.getCliente().getOrdini().stream()
                .filter(ordineCliente -> Boolean.TRUE.equals(ordineCliente.getClosed()))
                .count() + 1;

        if (numeroOrdini == 20) {
            return 20F;
        }

        if (numeroOrdini == 10) {
            return 10F;
        }

        return 0F;
    }

    @Override
    @Transactional(readOnly = true)
    public Float sommaRicaviTraDate(LocalDateTime dataInizio, LocalDateTime dataFine) {
        return ordineRepository.sumRicaviByDataOrdineBetween(dataInizio, dataFine);
    }

    @Override
    @Transactional(readOnly = true)
    public Float sommaCostiTraDate(LocalDateTime dataInizio, LocalDateTime dataFine) {
        return ordineRepository.sumCostiByDataOrdineBetween(dataInizio, dataFine);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer contaOrdiniTraDate(LocalDateTime dataInizio, LocalDateTime dataFine) {
        return ordineRepository.countOrdiniByDataOrdineBetween(dataInizio, dataFine);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer contaPizzeOrdinateTraDate(LocalDateTime dataInizio, LocalDateTime dataFine) {
        return ordineRepository.countPizzeOrdinateByDataOrdineBetween(dataInizio, dataFine);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> cercaClientiVirtuosi(LocalDateTime dataInizio, LocalDateTime dataFine) {
        return ordineRepository.findClientiVirtuosiByDataOrdineBetween(dataInizio, dataFine);
    }
}
