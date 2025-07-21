package com.evaitcsmatt.bookhub.webserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.exceptions.BookNotFoundException;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;
import com.evaitcsmatt.bookhub.webserver.utils.BookMapper;

@Service
@Primary
public class UserBookServiceImpl implements BookService<BookDto, PostNewBook> {
	
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
					return BookMapper.bookToBookDto(book);
				})
				.toList();
	}

	@Override
	public List<BookDto> getAllBooksByAuthor(String author) {
		return bookRepository
				.findAllByAuthor(author)
				.stream()
				.map(book -> {
					return BookMapper.bookToBookDto(book);
				})
				.toList();
	}

	@Override
	public List<BookDto> getAllBooksByGenre(String genre) {
		return bookRepository
				.findAllByGenreIgnoreCase(genre)
				.stream()
				.map(book -> {
					return BookMapper.bookToBookDto(book);
				})
				.toList();
	}

	@Override
	public BookDto getBookByTitle(String title) {
		Book book = bookRepository.findByTitle(title)
				.orElseThrow(() -> 
				new BookNotFoundException("Book with title ".concat(title).concat(" not found!")));
		return BookMapper.bookToBookDto(book);
	}

	@Override
	public BookDto getBookById(int id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> 
				new BookNotFoundException("Book with id "
						.concat(Integer.toString(id))
						.concat(" not found")));
		return BookMapper.bookToBookDto(book);
	}

	@Override
	public BookDto createBook(PostNewBook book) {
		Book newBook = bookRepository.save(
				new Book(0, 
						book.getTitle(), 
						book.getAuthor(), 
						book.getPublishDate(), 
						book.getGenre(), 
						book.getRating()));
		return BookMapper.bookToBookDto(newBook);
	}

	@Override
	public BookDto updateRating(int bookId, byte rating) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> 
		new BookNotFoundException("Book with id "
				.concat(Integer.toString(bookId))
				.concat(" not found")));
		book.setRating(rating);
		book = bookRepository.save(book);
		return BookMapper.bookToBookDto(book);
	}

	@Override
	public BookDto updateBook(BookDto book) {
		Book oldBook = bookRepository.findById(book.getId()).orElseThrow(() -> 
		new BookNotFoundException("Book with id "
				.concat(Integer.toString(book.getId()))
				.concat(" not found")));
		oldBook.setTitle(book.getTitle());
		oldBook.setAuthor(book.getAuthor());
		oldBook.setGenre(book.getGenre());
		oldBook.setRating(book.getRating());
		oldBook = bookRepository.save(oldBook);
		return BookMapper.bookToBookDto(oldBook);
	}

	@Override
	public void deleteBookById(int bookId) {
		bookRepository.deleteById(bookId);
	}

}
