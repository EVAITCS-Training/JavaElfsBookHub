package com.evaitcsmatt.bookhub.webserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaitcsmatt.bookhub.webserver.dto.PostNewUser;
import com.evaitcsmatt.bookhub.webserver.service.UserCredentialService;

@RestController
@RequestMapping("/api/v1/auth")
public class UserCredentialController {
	@Autowired
	private UserCredentialService userCredentialService;
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody PostNewUser postNewUser) {
		userCredentialService.register(postNewUser);
		return ResponseEntity.created(null).build();
		}
}
