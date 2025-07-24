package com.evaitcsmatt.bookhub.webserver.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredential implements UserDetails {
	@Id
	@Email(message = "Please provide a valid email address")
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Password is required")
	@Pattern(
		    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).*$",
		    message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
		)
	@Column(nullable = false)
	@Size(min = 8)
	private String password;
	@NotBlank(message = "Role is required")
	@Pattern(
	        regexp = "^(ADMIN|USER)$",
	        message = "Role must be either ADMIN or USER"
	    )
	private String role = "USER";
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return email;
	}
}
