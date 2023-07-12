package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Operatore;
import it.uniroma3.siw.spring.service.OperatoreService;



@Component
public class OperatoreValidator implements Validator {

	@Autowired
	OperatoreService operatoreService;
	
	@Override
	public boolean supports(Class<?> pClass) {
		return Operatore.class.equals(pClass);
	}
	
	public void validate(Object target, Errors errors) {
		if(this.operatoreService.alreadyExists((Operatore) target)) {
			errors.reject("operatore.duplicato");
		}		
	}

	

}
