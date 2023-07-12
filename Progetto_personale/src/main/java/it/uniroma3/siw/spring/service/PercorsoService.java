package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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


	public Percorso findById(Long id) {
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


	public void deleteById(Long id) {
		percorsoRepository.deleteById(id);
	}
	
	
	
}
