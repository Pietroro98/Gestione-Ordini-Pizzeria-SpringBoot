package it.prova.gestionalepizzeria.service;
import it.prova.gestionalepizzeria.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listAll();

    List<Cliente> findByExample(Cliente example);

    Cliente caricaSingoloElemento(Long id);

    void inserisciNuovo(Cliente cliente);

    void aggiorna(Cliente cliente);

    void attiva(Long id);

    void disattiva(Long id);
}
