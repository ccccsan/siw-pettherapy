package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Operatore;
import it.uniroma3.siw.spring.repository.OperatoreRepository;

@Service
public class OperatoreService {

	@Autowired
	private OperatoreRepository operatoreRepository;

	
	@Autowired
	private CredentialsService credentialsService;
	
	
	public void save(Operatore operatore) {
		operatoreRepository.save(operatore);
	}
	
	public void delete(Operatore operatore) {
		operatoreRepository.delete(operatore);
	}
	
	public boolean alreadyExists(Operatore operatore) {
		return operatoreRepository.existsByNomeAndCognome(operatore.getNome(), operatore.getCognome());
	}

	public Operatore findById(Long id) {
		return operatoreRepository.findById(id).get();
	}

	
	//mi ritorna la lista di tutti gli operatori
	public List<Operatore> findAll() {
		List<Operatore> operatori = new ArrayList<>();
		for(Operatore o : operatoreRepository.findAll()) {
			operatori.add(o);
		}
		return operatori;
	}

	public void deleteById(Long id) {
		operatoreRepository.deleteById(id);
	}
	
	
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
	
	
}
