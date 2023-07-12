package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	private String email;

	// un utente può intraprendere un solo percorso
	@ManyToOne
	private Percorso percorso;

	/* SETTERS & GETTERS */
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Percorso getPercorso() {
		return percorso;
	}

	public void setPercorso(Percorso percorso) {
		this.percorso = percorso;
	}

}
