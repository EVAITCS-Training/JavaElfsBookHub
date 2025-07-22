package com.evaitcsmatt.bookhub.webserver.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.utils.BookServiceFactory;

import jakarta.validation.Valid;

@Controller
public class BookController {
	
	private final BookServiceFactory bookServiceFactory;
	
	public BookController(BookServiceFactory bookServiceFactory) {
		this.bookServiceFactory = bookServiceFactory;
	}
	
	@GetMapping(value = {"/books", "/books/"})
	public String getBookMenu(Model model) {
		model.addAttribute("listOfBooks", bookServiceFactory.getBookService(null).getAllBooks());
		return "book-menu";
	}
	
	@GetMapping(value = {"/books/search", "/books/search/"})
	public String getSearchMenu() {
		return "search";
	}
	
	@GetMapping(value = "/books/search/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDto> getTitleSearchResults(@PathVariable String title) {
		return ResponseEntity.ok(bookServiceFactory.getBookService(null).getBookByTitle(title));
	}
	
	@GetMapping(value = {"/books/add", "/books/add/"})
	public String addBook(Model model) {
		model.addAttribute("newBook", new PostNewBook());
		return "add-book-form";
	}
	
	@PostMapping(value = {"/books/add", "/books/add/"})
	public String addBook(@ModelAttribute("newBook") @Valid PostNewBook book, Errors errors) {
		if(errors.hasErrors()) {
			return "add-book-form";
		}
		bookServiceFactory.getBookService(null).createBook(book);
		return "redirect:/books/";
	}
}
