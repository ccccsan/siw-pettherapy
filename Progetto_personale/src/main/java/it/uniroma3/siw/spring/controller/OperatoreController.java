package it.uniroma3.siw.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.OperatoreValidator;
import it.uniroma3.siw.spring.model.Operatore;
import it.uniroma3.siw.spring.service.OperatoreService;
import it.uniroma3.siw.spring.session.SessionDataUser;

@Controller
public class OperatoreController {

	@Autowired
	private OperatoreService operatoreService;

	@Autowired
	private OperatoreValidator operatoreValidator;

	@Autowired
	private SessionDataUser sessionDataUser;

	@PostMapping("/operatore")
	public String addOperatore(@Valid @ModelAttribute("operatore") Operatore operatore, BindingResult bindingResult,
			Model model) {

		operatoreValidator.validate(operatore, bindingResult);

		if (!bindingResult.hasErrors()) {
			operatoreService.save(operatore);
			model.addAttribute("operatore", operatore);
			model.addAttribute("elencoOperatore", operatoreService.findAll());

			model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());

			return "operatore.html";
		}

		return "operatoreForm.html";
	}

	// form dell'operatore
	@GetMapping("/operatoreForm")
	public String getOperatoreForm(Model model) {
		model.addAttribute("operatore", new Operatore());

		return "operatoreForm.html";
	}

	// prendo un operatore per il suo id
	@GetMapping("/operatore/{id}")
	public String getOperatore(@PathVariable("id") Long id, Model model) {
		Operatore operatore = operatoreService.findById(id);
		model.addAttribute("operatore", operatore);

		return "operatore.html";
	}

	// prendo l'elenco degli operatori senza id
	@GetMapping("/elencoOperatori")
	public String getElencoOperatori(Model model) {

		List<Operatore> elencoOperatori = operatoreService.findAll();
		model.addAttribute("elencoOperatori", elencoOperatori);
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());

		return "elencoOperatori.html";
	}

	// se clicco su cancella mi porta alla pagina di conferma
	@GetMapping("/admin/toDeleteOperatore/{id}")
	public String toDeleteChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("operatore", operatoreService.findById(id));

		return "toDeleteOperatore.html";
	}

	// confermo la cancellazione dell'operatore tramite id
	@GetMapping("/admin/deleteOperatore/{id}")
	public String deleteOPeratore(@PathVariable("id") Long id, Model model) {
		operatoreService.deleteById(id);
		model.addAttribute("elencoBuffet", operatoreService.findAll());
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());

		return "redirect:/elencoOperatori";
	}

}
