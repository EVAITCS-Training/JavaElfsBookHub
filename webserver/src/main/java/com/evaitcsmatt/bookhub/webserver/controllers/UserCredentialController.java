package com.evaitcsmatt.bookhub.webserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.evaitcsmatt.bookhub.webserver.dto.PostNewUser;
import com.evaitcsmatt.bookhub.webserver.service.UserCredentialService;

@Controller
public class UserCredentialController {
	@Autowired
	private UserCredentialService userCredentialService;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new PostNewUser());
		return "sign-up";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("newUser") PostNewUser postNewUser) {
		userCredentialService.register(postNewUser);
		return "redirect:/login";
	}
}
