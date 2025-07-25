package com.evaitcsmatt.bookhub.webserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.evaitcsmatt.bookhub.webserver.dto.PostNewUser;
import com.evaitcsmatt.bookhub.webserver.entities.UserCredential;
import com.evaitcsmatt.bookhub.webserver.repositories.UserCredentialRepository;

@Service
public class UserCredentialService {
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void register(PostNewUser postNewUser) {
		UserCredential userCredential = UserCredential
				.builder()
				.email(postNewUser.getEmail())
				.password(passwordEncoder.encode(postNewUser.getPassword()))
				.role("USER")
				.build();
		userCredentialRepository.save(userCredential);
	}
}
