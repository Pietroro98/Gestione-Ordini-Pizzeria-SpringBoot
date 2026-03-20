package it.prova.gestionalepizzeria.dto;
import it.prova.gestionalepizzeria.model.Ordine;
import it.prova.gestionalepizzeria.model.Pizza;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdineDTO {

    private Long id;

    @NotNull(message = "Il cliente è obbligatorio")
    private ClienteDTO cliente;

    private List<PizzaDTO> pizze = new ArrayList<>();

    @NotNull(message = "La data ordine è obbligatoria")
    private LocalDateTime dataOrdine;

    private Boolean closed = Boolean.FALSE;

    @NotBlank(message = "Il codice è obbligatorio")
    private String codice;

    private Float costoTotale;

    @NotNull(message = "Per poter procedere con  l'ordine devi prima selezionare una pizza")
    private Long pizzaId;

    public OrdineDTO() {
    }

    public static OrdineDTO buildFromModel(Ordine model, boolean includeDetails) {
        OrdineDTO result = new OrdineDTO();
        result.setId(model.getId());
        result.setDataOrdine(model.getDataOrdine());
        result.setClosed(model.getClosed());
        result.setCodice(model.getCodice());
        result.setCostoTotale(model.getCostoTotale());
        if (model.getCliente() != null) {
            result.setCliente(ClienteDTO.buildFromModel(model.getCliente()));
        }
        if (includeDetails && model.getPizze() != null) {
            List<PizzaDTO> pizzaList = model.getPizze().stream().map(PizzaDTO::BuildFromModel).collect(Collectors.toList());
            result.setPizze(pizzaList);
            result.setPizzaId(pizzaList.isEmpty() ? null : pizzaList.get(0).getId());
        }
        return result;
    }

    public static List<OrdineDTO> createListFromModelList(List<Ordine> input, boolean includeDetails) {
        return input.stream().map(element -> buildFromModel(element, includeDetails)).collect(Collectors.toList());
    }

    public Ordine buildModel() {
        Ordine ordine = new Ordine();
        ordine.setId(id);
        ordine.setDataOrdine(dataOrdine);
        ordine.setClosed(closed == null ? Boolean.FALSE : closed);
        ordine.setCodice(codice);
        ordine.setCostoTotale(costoTotale);
        if (cliente != null && cliente.getId() != null) {
            ordine.setCliente(cliente.buildModel());
        }
        if (pizzaId != null) {
            ordine.setPizze(List.of(new Pizza(pizzaId)).stream().collect(Collectors.toSet()));
        }
        return ordine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<PizzaDTO> getPizze() {
        return pizze;
    }

    public void setPizze(List<PizzaDTO> pizze) {
        this.pizze = pizze;
    }

    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Float getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(Float costoTotale) {
        this.costoTotale = costoTotale;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }
}
