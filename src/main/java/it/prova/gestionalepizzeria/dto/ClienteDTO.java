package it.prova.gestionalepizzeria.dto;

import it.prova.gestionalepizzeria.model.Cliente;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "L'indirizzo è obbligatorio")
    private String indirizzo;

    private Boolean attivo = Boolean.TRUE;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id) {
        this.id = id;
    }

    public ClienteDTO(Long id, String nome, String cognome, String indirizzo, Boolean attivo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.attivo = attivo;
    }

    public static ClienteDTO buildFromModel(Cliente model) {
        return new ClienteDTO(model.getId(), model.getNome(), model.getCognome(), model.getIndirizzo(),
                model.getAttivo());
    }

    public static List<ClienteDTO> createListFromModelList(List<Cliente> input) {
        return input.stream().map(ClienteDTO::buildFromModel).collect(Collectors.toList());
    }

    public Cliente buildModel() {
        return new Cliente(id, nome, cognome, indirizzo, attivo == null ? Boolean.TRUE : attivo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
}
