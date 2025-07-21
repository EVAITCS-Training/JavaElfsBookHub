package com.evaitcsmatt.bookhub.webserver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evaitcsmatt.bookhub.webserver.dto.AdminBookDto;
import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBookAdmin;
import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.exceptions.BookNotFoundException;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;
import com.evaitcsmatt.bookhub.webserver.utils.BookMapper;

@Service("admin")
public class AdminBookServiceImpl implements BookService<AdminBookDto, PostNewBookAdmin> {
	
	private final BookRepository bookRepository;
	
	public AdminBookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<AdminBookDto> getAllBooks() {
		return bookRepository
				.findAll()
				.stream()
				.map(book -> {
					return BookMapper.bookToAdminBookDto(book);
				})
				.toList();
	}

	@Override
	public List<AdminBookDto> getAllBooksByAuthor(String author) {
		return bookRepository
				.findAllByAuthor(author)
				.stream()
				.map(book -> {
					return BookMapper.bookToAdminBookDto(book);
				})
				.toList();
	}

	@Override
	public List<AdminBookDto> getAllBooksByGenre(String genre) {
		return bookRepository
				.findAllByGenreIgnoreCase(genre)
				.stream()
				.map(book -> {
					return BookMapper.bookToAdminBookDto(book);
				})
				.toList();
	}

	@Override
	public AdminBookDto getBookByTitle(String title) {
		Book book = bookRepository.findByTitle(title)
				.orElseThrow(() -> 
				new BookNotFoundException("Book with title ".concat(title).concat(" not found!")));
		return BookMapper.bookToAdminBookDto(book);
	}

	@Override
	public AdminBookDto getBookById(int id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> 
		new BookNotFoundException("Book with id "
				.concat(Integer.toString(id))
				.concat(" not found")));
		return BookMapper.bookToAdminBookDto(book);
	}

	@Override
	public AdminBookDto createBook(PostNewBookAdmin book) {
		Book newBook = bookRepository.save(
				new Book(0, 
						book.getTitle(), 
						book.getAuthor(), 
						book.getPublishDate(), 
						book.getGenre(), 
						book.getRating(),
						book.getPrice()
						));
		return BookMapper.bookToAdminBookDto(newBook);
	}

	@Override
	public AdminBookDto updateRating(int bookId, byte rating) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> 
		new BookNotFoundException("Book with id "
				.concat(Integer.toString(bookId))
				.concat(" not found")));
		book.setRating(rating);
		book = bookRepository.save(book);
		return BookMapper.bookToAdminBookDto(book);
	}

	@Override
	public AdminBookDto updateBook(AdminBookDto book) {
		Book oldBook = bookRepository.findById(book.getId()).orElseThrow(() -> 
		new BookNotFoundException("Book with id "
				.concat(Integer.toString(book.getId()))
				.concat(" not found")));
		oldBook.setTitle(book.getTitle());
		oldBook.setAuthor(book.getAuthor());
		oldBook.setGenre(book.getGenre());
		oldBook.setRating(book.getRating());
		oldBook.setPrice(book.getPrice());
		oldBook = bookRepository.save(oldBook);
		return BookMapper.bookToAdminBookDto(oldBook);
	}

	@Override
	public void deleteBookById(int bookId) {
		bookRepository.deleteById(bookId);
	}

}
