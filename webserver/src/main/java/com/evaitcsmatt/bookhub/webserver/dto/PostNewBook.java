package com.evaitcsmatt.bookhub.webserver.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostNewBook {
	@Size(min = 2, max = 150, message = "Title has to between 2 and 150 characters long!")
	private String title;
	@Size(min = 3, max = 300)
	private String author;
	private String genre;
	private LocalDate publishDate;
	private byte rating;
}
