package it.prova.gestionalepizzeria.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pizza")
	private Long id;

	@Column(name = "descrizione", nullable = false)
	private String descrizione;

	@Column(name = "ingredienti")
	private String ingredienti;

	@Column(name = "prezzo_base", nullable = false)
	private Float prezzoBase;

	@Column(name = "attivo", nullable = false)
	private Boolean attivo;

	@ManyToMany(mappedBy = "pizze", fetch = FetchType.LAZY)
	private Set<Ordine> ordini = new HashSet<>();

	public Pizza() {
	}

	public Pizza(Long id) {
		this.id = id;
	}

	public Pizza(Long id, String descrizione, String ingredienti, Float prezzoBase, Boolean attivo) {
		this.id = id;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzoBase = prezzoBase;
		this.attivo = attivo;
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

	public Set<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<Ordine> ordini) {
		this.ordini = ordini;
	}
}
