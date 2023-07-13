package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class Cane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //chiave primaria nel mapping

    @NotBlank
    private String nome;

    @NotBlank
    private String razza;

    @NotBlank
    private String curriculum;

    @Column(nullable = true, length = 10000000)
    private String photos;


    public Cane() {
    }

    public Cane(Long id, @NotBlank String nome, @NotBlank String razza, @NotBlank String curriculum,
                @NotBlank String photos, Operatore operatore, Percorso percorso) {
        super();
        this.id = id;
        this.nome = nome;
        this.razza = razza;
        this.curriculum = curriculum;
        this.photos = photos;
        this.operatore = operatore;
        this.percorso = percorso;
    }


    //un operatore addestra più cani
    @ManyToOne
    private Operatore operatore;


    //più cani possono intraprendere lo stesso percorso
    @ManyToOne
    private Percorso percorso;


    /* SETTER & GETTER */
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

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public Operatore getOperatore() {
        return operatore;
    }

    public void setOperatore(Operatore operatore) {
        this.operatore = operatore;
    }

    public Percorso getPercorso() {
        return percorso;
    }

    public void setPercorso(Percorso percorso) {
        this.percorso = percorso;
    }


}
