package it.uniroma3.siw.spring.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.model.Percorso;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.PercorsoService;
import it.uniroma3.siw.spring.service.UserService;
import it.uniroma3.siw.spring.session.SessionDataUser;



@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PercorsoService percorsoService;
	
	@Autowired
	private SessionDataUser sessionDataUser;

	
	
	
	@PostMapping("/user")
	public String addUser(@RequestParam Long percorsoid, Model model) {
		
			User user = sessionDataUser.getLoggedCredentials().getUser();
			Percorso percorso = percorsoService.findById(percorsoid);
			user.setPercorso(percorso);
			
			userService.saveUser(user);
			model.addAttribute("elencoPercorsi", percorsoService.findAll());
			

			
			return "risposta.html";
		
	}
	
	
	
	//prendo l'user per il suo id
	@GetMapping("/areaRiservata")
	public String getUser(Model model) {
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());
		
		return "user.html";
    }
	
	
	//prendo l'elenco degli user 
	@GetMapping("/elencoUsers")
	public String getElencoUsers(Model model) {
		
		List<User> elencoUsers = userService.getAllUsers();
		model.addAttribute("elencoUsers", elencoUsers);

        return "elencoUsers.html";
    }
	
	
	
	
	//mi ritorna la form
	@GetMapping("/userForm") 
	public String creaUser(Model model) {

		model.addAttribute("elencoPercorsi", percorsoService.findAll());
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());

		return "userForm.html";
	}
	
	
	
	@PostMapping("/userForm")
	public String save(User user) {
		userService.saveUser(user);
		
		return "redirect:/userForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
