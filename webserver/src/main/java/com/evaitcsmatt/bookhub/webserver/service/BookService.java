package com.evaitcsmatt.bookhub.webserver.service;

import java.util.List;

import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.entities.Book;

public interface BookService<T extends BookDto, P extends PostNewBook> {
	List<T> getAllBooks();
	List<T> getAllBooksByAuthor(String author);
	List<T> getAllBooksByGenre(String genre);
	T getBookByTitle(String title);
	T getBookById(int id);
	T createBook(P book);
	T updateRating(int bookId, byte rating);
	T updateBook(T book);
	void deleteBookById(int bookId);
}
