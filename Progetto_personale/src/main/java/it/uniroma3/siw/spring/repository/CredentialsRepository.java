package it.uniroma3.siw.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findByUsername(String username);

}
