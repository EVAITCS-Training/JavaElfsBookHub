package com.evaitcsmatt.bookhub.webserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
	
	@GetMapping("/")
	public String index() {
		return "forward:/index.html";
	}

	@GetMapping(value = {
		"/books",
		"/add-book",
		"/register",
		"/login"
	})
	public String reactRoutes() {
		return "forward:/index.html";
	}
}
