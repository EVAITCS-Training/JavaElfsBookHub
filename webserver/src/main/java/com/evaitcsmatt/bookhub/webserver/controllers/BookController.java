package com.evaitcsmatt.bookhub.webserver.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.utils.BookServiceFactory;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	
	private final BookServiceFactory bookServiceFactory;
	
	public BookController(BookServiceFactory bookServiceFactory) {
		this.bookServiceFactory = bookServiceFactory;
	}
	
	@GetMapping(value = {"/", ""})
	public ResponseEntity<List<BookDto>> getBookMenu() {
		return ResponseEntity.ok(bookServiceFactory.getBookService(null).getAllBooks());
	}
	
	@GetMapping(value = "/search/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDto> getTitleSearchResults(@PathVariable String title) {
		return ResponseEntity.ok(bookServiceFactory.getBookService(null).getBookByTitle(title));
	}
	
	@PostMapping(value = {"/add", "/add/"})
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid PostNewBook book) {
		return ResponseEntity.created(null).body(bookServiceFactory.getBookService(null).createBook(book));
	}
	
	@PatchMapping("/update/{id}/rating/{rating}")
	public ResponseEntity<BookDto> updateRating(@PathVariable int id, @PathVariable byte rating) {
		return ResponseEntity.ok(bookServiceFactory.getBookService(null).updateRating(id, rating));
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		bookServiceFactory.getBookService(null).deleteBookById(id);
		return ResponseEntity.noContent().build();
	}
}
