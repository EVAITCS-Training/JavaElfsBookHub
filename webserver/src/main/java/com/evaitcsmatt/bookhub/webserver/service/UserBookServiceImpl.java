package com.evaitcsmatt.bookhub.webserver.service;

import java.util.ArrayList;
import java.util.List;

import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.exceptions.BookNotFoundException;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;

public class UserBookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	
	public UserBookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<BookDto> getAllBooks() {
		return bookRepository
				.findAll()
				.stream()
				.map(book -> {
					return new BookDto(
							book.getId(), 
							book.getTitle(), 
							book.getAuthor(), 
							book.getGenre(), 
							book.getRating()
							);
				})
				.toList();
	}

	@Override
	public List<BookDto> getAllBooksByAuthor(String author) {
		return bookRepository
				.findAllByAuthor(author)
				.stream()
				.map(book -> {
					return new BookDto(
							book.getId(), 
							book.getTitle(), 
							book.getAuthor(), 
							book.getGenre(), 
							book.getRating()
							);
				})
				.toList();
	}

	@Override
	public List<BookDto> getAllBooksByGenre(String genre) {
		return bookRepository
				.findAllByGenreIgnoreCase(genre)
				.stream()
				.map(book -> {
					return new BookDto(
							book.getId(), 
							book.getTitle(), 
							book.getAuthor(), 
							book.getGenre(), 
							book.getRating()
							);
				})
				.toList();
	}

	@Override
	public BookDto getBookByTitle(String title) {
		Book book = bookRepository.findByTitle(title)
				.orElseThrow(() -> 
				new BookNotFoundException("Book with title ".concat(title).concat(" not found!")));
		return new BookDto(
				book.getId(), 
				book.getTitle(), 
				book.getAuthor(), 
				book.getGenre(), 
				book.getRating());
	}

	@Override
	public BookDto getBookById(int id) {
		Book book = bookRepository.findById(id)
		return null;
	}

	@Override
	public BookDto createBook(PostNewBook book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto updateRating(int bookId, byte rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto updateBook(BookDto book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBookById(int bookId) {
		// TODO Auto-generated method stub
		
	}

}
