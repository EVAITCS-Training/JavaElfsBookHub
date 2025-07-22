package com.evaitcsmatt.bookhub.webserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.evaitcsmatt.bookhub.webserver.service.BookService;
import com.evaitcsmatt.bookhub.webserver.utils.BookServiceFactory;

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
}
