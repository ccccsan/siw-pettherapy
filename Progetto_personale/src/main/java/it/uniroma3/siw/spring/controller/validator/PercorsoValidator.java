package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Percorso;
import it.uniroma3.siw.spring.service.PercorsoService;


@Component
public class PercorsoValidator implements Validator {

	@Autowired 
	private PercorsoService percorsoService;

	@Override
	public boolean supports(Class<?> pClass) {
		return Percorso.class.equals(pClass);
	}


	@Override
	public void validate(Object target, Errors errors) {
		//downcast di object
		if(this.percorsoService.alreadyExists((Percorso) target)) {
			errors.reject("chef.duplicato");
		}

	}

}
