package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
