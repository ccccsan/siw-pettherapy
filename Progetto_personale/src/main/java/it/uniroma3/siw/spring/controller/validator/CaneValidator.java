package it.uniroma3.siw.spring.controller.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Cane;
import it.uniroma3.siw.spring.service.CaneService;

@Component
public class CaneValidator implements Validator {

	@Autowired 
	private CaneService caneService;

	@Override
	public boolean supports(Class<?> pClass) {
		return Cane.class.equals(pClass);
	}


	@Override
	public void validate(Object target, Errors errors) {
		if(this.caneService.alreadyExists((Cane) target)) {
			errors.reject("cane.duplicato");
		}		
	}

}
