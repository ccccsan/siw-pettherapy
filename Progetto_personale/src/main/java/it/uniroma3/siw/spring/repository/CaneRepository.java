package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Cane;
import it.uniroma3.siw.spring.model.Operatore;

public interface CaneRepository extends CrudRepository<Cane, Long> {
	
	//mi costruisco un metodo che mi dice se esiste un cane con quel microchip 
			public boolean existsByNome(String nome);

			public List<Cane> findByOperatore(Operatore operatore);

}
