package it.prova.gestionalepizzeria.service;

import it.prova.gestionalepizzeria.model.Cliente;
import it.prova.gestionalepizzeria.model.Pizza;
import it.prova.gestionalepizzeria.repository.cliente.ClienteRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findByExample(Cliente example) {
        Specification<Cliente> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(example.getNome())) {
                predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));
            }
            if (StringUtils.isNotBlank(example.getCognome())) {
                predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));
            }
            if (StringUtils.isNotBlank(example.getIndirizzo())) {
                predicates.add(cb.like(cb.upper(root.get("indirizzo")), "%" + example.getIndirizzo().toUpperCase() + "%"));
            }
            if (example.getAttivo() != null) {
                predicates.add(cb.equal(root.get("attivo"), example.getAttivo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return clienteRepository.findAll(specification);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente caricaSingoloElemento(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void inserisciNuovo(Cliente cliente) {
        cliente.setAttivo(Boolean.TRUE);
        clienteRepository.save(cliente);
    }

    @Override
    public void aggiorna(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void attiva(Long id) {
        Cliente existing = caricaSingoloElemento(id);
        if (existing == null) {
            return;
        }
        existing.setAttivo(Boolean.TRUE);
        clienteRepository.save(existing);
    }

    @Override
    @Transactional
    public void disattiva(Long id) {
        Cliente existing = caricaSingoloElemento(id);
        if (existing == null) {
            return;
        }
        existing.setAttivo(Boolean.FALSE);
        clienteRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> searchAttiviByTerm(String term) {
        return clienteRepository.searchAttiviByTerm(term);
    }
}
