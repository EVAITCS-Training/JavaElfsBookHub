package com.evaitcsmatt.bookhub.webserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostNewUser {
	private String email;
	private String password;
}
