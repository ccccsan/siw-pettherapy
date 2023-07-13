package it.uniroma3.siw.spring.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.CaneValidator;
import it.uniroma3.siw.spring.model.Cane;
import it.uniroma3.siw.spring.model.Operatore;
import it.uniroma3.siw.spring.service.CaneService;
import it.uniroma3.siw.spring.service.OperatoreService;
import it.uniroma3.siw.spring.service.PercorsoService;
import it.uniroma3.siw.spring.session.SessionDataUser;


@Controller
public class CaneController {

	@Autowired
	private CaneService caneService;

	@Autowired
	private CaneValidator caneValidator;

	@Autowired
	private OperatoreService operatoreService;

	@Autowired
	private PercorsoService percorsoService;

	@Autowired
	private SessionDataUser sessionDataUser;


	@PostMapping("/cane")
	public String addCane(@RequestParam("idOperatore") String idOperatore, @Valid @ModelAttribute ("cane") Cane cane,
			@RequestParam("image") MultipartFile multipartFile, BindingResult bindingResult, Model model) throws IOException {

		/*
		 * codice ridondante ma che serve per il passaggio dei parametri
		 */
		Long id = Long.valueOf(idOperatore);
		Operatore operatore = operatoreService.findById(id);
		cane.setOperatore(operatore);


		caneValidator.validate(cane, bindingResult);

		if(!bindingResult.hasErrors()) {
			String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());;
			cane.setPhotos(base64Image);
			caneService.save(cane);
			model.addAttribute("cane", cane);
			model.addAttribute("elencoCani", caneService.getAllCani());
			model.addAttribute("elencoPercorsi", percorsoService.findAll());
			model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());


			return "elencoCani.html";

		}
		//se qualcosa è andato storto, torno alla form
		return "caneForm.html";

	}


	@GetMapping ("/cane/{id}")
	public String getCane(@PathVariable("id") Long id, Model model) {
		Cane cane = caneService.findById(id);
		model.addAttribute("cane", cane);

		model.addAttribute("percorso", cane.getPercorso());

		return "cane.html";
	}


	//elenco dei cani senza id operatore
	@GetMapping("/elencoCani")
	public String getElencoCani(Model model) {
		List<Cane> elencoCani = caneService.getAllCani();
		model.addAttribute("elencoCani", elencoCani);
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());


		return "elencoCani.html";
	}


	//elenco dei cani con id operatore
	@GetMapping("/operatore/{id}/elencoCani")
	public String getElencoCaniId(@PathVariable("id") Long id, Model model) {
		Operatore operatore = operatoreService.findById(id);
		List<Cane> elencoCani = caneService.getByOperatore(operatore);
		model.addAttribute("elencoCani", elencoCani);
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());


		return "elencoCani.html";
	}


	//collego il cane al suo operatore tramite l'id di quest'ultimo
	@GetMapping("/admin/operatore/{id}/caneForm")
	public String creaCane(@PathVariable("id") Long id, Model model) {

		Operatore operatore = operatoreService.findById(id);
		Cane cane = new Cane();
		cane.setOperatore(operatore);

		model.addAttribute("cane", cane);
		model.addAttribute("elencoCani", caneService.getAllCani());
		model.addAttribute("elencoPercorsi", percorsoService.findAll());


		return "caneForm.html";
	}



	@PostMapping("/admin/operatore/{id}/caneForm")
	public String save(Cane cane) {
		caneService.save(cane);

		return "redirect:/admin/operatore/{id}/caneForm";
	}




	//mi chiede la conferma per cancellare un cane
	@GetMapping("/admin/toDeleteCane/{id}")
	public String toDeleteCane(@PathVariable("id") Long id, Model model) {

		model.addAttribute("cane", caneService.findById(id));

		return "toDeleteCane.html";
	}



	//mi cancella il cane tramite il suo id
	@GetMapping("/admin/deleteCane/{id}")
	public String deleteCane(@PathVariable("id") Long id, Model model) {
		caneService.deleteById(id);
		model.addAttribute("elencoCani", caneService.getAllCani());
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());


		return "elencoCani.html";
	}




}




