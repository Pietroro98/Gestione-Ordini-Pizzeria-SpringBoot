package it.prova.gestionalepizzeria.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ordine")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ordine_pizzeria_pizza",
			joinColumns = @JoinColumn(name = "ordine_id"),
			inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	private Set<Pizza> pizze = new HashSet<>();

	@Column(name = "data_ordine", nullable = false)
	private LocalDateTime dataOrdine;

	@Column(name = "closed")
	private Boolean closed;

	@Column(name = "codice", nullable = false, unique = true)
	private String codice;

	@Column(name = "costo_totale", nullable = false)
	private Float costoTotale;

	public Ordine() {
	}

	public Ordine(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
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
}
