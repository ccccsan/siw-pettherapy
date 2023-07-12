package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import it.uniroma3.siw.spring.model.Cane;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Percorso;
import it.uniroma3.siw.spring.repository.PercorsoRepository;

@Service
public class PercorsoService {

	@Autowired
	private PercorsoRepository percorsoRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	
	
	@Transactional
	public void save(Percorso percorso) {
		percorsoRepository.save(percorso);
	}


	public Percorso findPercorsoById(Long id) {
		return percorsoRepository.findById(id).get();
	}


	public List<Percorso> findAll() {
		List<Percorso> elencoPercorsi = new ArrayList<>();
		for(Percorso p : percorsoRepository.findAll()) {
			elencoPercorsi.add(p);
		}
		return elencoPercorsi;
	}
	
	
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
	
	
	public boolean alreadyExists(Percorso percorso) {
		return percorsoRepository.existsByNome(percorso.getNome());
	}

	@Transactional
	public Percorso updatePercorso(Percorso oldPercorso, Percorso newPercorso) {
		oldPercorso.setDescrizione(newPercorso.getDescrizione());
		return this.percorsoRepository.save(oldPercorso);
	}
//	@Transactional
//	public void deletePercorso(Long id) {
//		Percorso percorso = this.findPercorsoById(id);
//		List<Cane> cani = percorso.getCani();
//		for (Cane c : cani) {
//			c.setPercorso(null);
//		}
//		List<User> users = percorso.getUsers();
//		for (User u : users) {
//			u.setPercorso(null);
//		}
//		percorsoRepository.deleteById(id);
//	}
	
	
	
}
