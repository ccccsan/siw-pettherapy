package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Percorso;


public interface PercorsoRepository extends CrudRepository<Percorso, Long>{

	boolean existsByNome(String nome);


}
