package com.evaitcsmatt.bookhub.webserver.service;

import java.util.List;

import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.entities.Book;

public interface BookService {
	List<BookDto> getAllBooks();
	List<BookDto> getAllBooksByAuthor(String author);
	List<BookDto> getAllBooksByGenre(String genre);
	BookDto getBookByTitle(String title);
	BookDto getBookById(int id);
}
