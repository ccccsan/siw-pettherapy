package it.uniroma3.siw.spring.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Percorso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // chiave primaria nel mapping

	@NotBlank
	private String nome;

	@NotBlank
	private String descrizione;

	// percorso insegnato a più cani
	@OneToMany(mappedBy = "operatore", cascade = CascadeType.ALL)
	private List<Cane> cani;

	// un percorso può essere scelto da più utenti
	@OneToMany(mappedBy = "percorso", cascade = CascadeType.REMOVE)
	private List<User> users;

	public Percorso() {
		this.users = new LinkedList<>();
		this.cani = new LinkedList<>();
	}

	/* SETTER & GETTERS */
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Cane> getCani() {
		return cani;
	}

	public void setCani(List<Cane> cani) {
		this.cani = cani;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
