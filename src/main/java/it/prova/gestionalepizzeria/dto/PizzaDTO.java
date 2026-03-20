package it.prova.gestionalepizzeria.dto;

import it.prova.gestionalepizzeria.model.Pizza;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public class PizzaDTO {

    private Long id;

    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;

    @NotBlank(message = "Gli ingredienti sono obbligatori")
    private String ingredienti;

    @NotNull(message = "Il prezzo base è obbligatorio")
    @Min(value = 1, message = "Il prezzo minimo da poter inserire è 1 €")
    private Float prezzoBase;

    private Boolean attivo = Boolean.TRUE;

    public PizzaDTO() {
    }

    public PizzaDTO(Long id) {
        this.id = id;
    }

    public PizzaDTO(Long id, String descrizione, String ingredienti, Float prezzoBase, Boolean attivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzoBase = prezzoBase;
        this.attivo = attivo;
    }


    public static PizzaDTO BuildFromModel(Pizza model) {
        return new PizzaDTO(model.getId(), model.getDescrizione(), model.getIngredienti(), model.getPrezzoBase(), model.getAttivo());
    }

    public static List<PizzaDTO> createListFromModelList(List<Pizza> input) {
        return input.stream().map(PizzaDTO::BuildFromModel).collect(Collectors.toList());
    }

    public Pizza buildModel() {
        return new Pizza(id, descrizione, ingredienti, prezzoBase, attivo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public Float getPrezzoBase() {
        return prezzoBase;
    }

    public void setPrezzoBase(Float prezzoBase) {
        this.prezzoBase = prezzoBase;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
}
