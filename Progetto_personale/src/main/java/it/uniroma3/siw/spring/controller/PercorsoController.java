package it.uniroma3.siw.spring.controller;

import java.util.List;

import javax.validation.Valid;

import it.uniroma3.siw.spring.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.PercorsoValidator;
import it.uniroma3.siw.spring.model.Percorso;
import it.uniroma3.siw.spring.service.PercorsoService;
import it.uniroma3.siw.spring.session.SessionDataUser;

@Controller
public class PercorsoController {

	@Autowired
	private PercorsoService percorsoService;

	@Autowired
	private PercorsoValidator percorsoValidator;

	@Autowired
	private SessionDataUser sessionDataUser;

	@PostMapping("/percorso")
	public String addPercorso(@Valid @ModelAttribute("percorso") Percorso percorso, BindingResult bindingResult,
			Model model) {

		percorsoValidator.validate(percorso, bindingResult);

		if (!bindingResult.hasErrors()) {

			Credentials credentials = sessionDataUser.getLoggedCredentials();
			percorsoService.save(percorso);
			model.addAttribute("percorso", percorso);
			model.addAttribute("elencoPercorsi", percorsoService.findAll());
			model.addAttribute("loggedCredential", credentials);

			return "elencoPercorsi.html";
		}
		return "percorsoForm.html";

	}

	// form del percorso
	@GetMapping("/percorsoForm")
	public String getPercorsoForm(Model model) {
		model.addAttribute("percorso", new Percorso());

		return "percorsoForm.html";
	}

	@GetMapping("/percorso/{id}")
	public String getPercorso(@PathVariable("id") Long id, Model model) {
		Percorso percorso = percorsoService.findPercorsoById(id);
		model.addAttribute("percorso", percorso);

		return "percorso.html";
	}

	@GetMapping("/elencoPercorsi")
	public String getElencoPercorsi(Model model) {
		List<Percorso> elencoPercorsi = percorsoService.findAll();

		Credentials credentials = sessionDataUser.getLoggedCredentials();

		model.addAttribute("elencoPercorsi", elencoPercorsi);
		model.addAttribute("loggedCredential", credentials);

		return "elencoPercorsi.html";
	}

	// se clicco su modifica mi porta alla pagina di modificare
	@GetMapping("/admin/toUpdatePercorso/{id}")
	public String toDeletePercorso(@PathVariable("id") Long id, Model model) {
		model.addAttribute("percorso", percorsoService.findPercorsoById(id));
		return "toUpdatePercorso.html";
	}

	// modifica del percorso
	@PostMapping("/admin/updatePercorso/{id}")
	public String updatePercorso(@ModelAttribute("percorso") Percorso newPercorso, @PathVariable("id") Long id, Model model) {
//		percorsoService.deletePercorso(id);
		Percorso oldPercorso = this.percorsoService.findPercorsoById(id);
		Percorso percorso = this.percorsoService.updatePercorso(oldPercorso, newPercorso);
		Credentials credentials = sessionDataUser.getLoggedCredentials();
		this.percorsoService.save(percorso);
		model.addAttribute("elencoPercorsi", percorsoService.findAll());
		model.addAttribute("loggedCredential", credentials);

		return "elencoPercorsi.html";
	}

}
