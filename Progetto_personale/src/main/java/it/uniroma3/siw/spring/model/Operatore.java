package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Operatore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // chiave primaria nel mapping

	@NotBlank
	private String nome;

	@NotBlank
	private String cognome;

	@NotBlank
	private String specializzazione;

	@NotNull
	@Min(0)
	@Max(120)
	private Integer eta; // Integer perché così ho anche il valore nullo

	// un operatore addestra più cani
	@OneToMany(mappedBy = "operatore", cascade = CascadeType.ALL)
	private List<Cane> cani;

	// SETTER e GETTER
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

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public String getSpecializzazione() {
		return specializzazione;
	}

	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}

	public List<Cane> getCani() {
		return cani;
	}

	public void setCani(List<Cane> cani) {
		this.cani = cani;
	}

}
