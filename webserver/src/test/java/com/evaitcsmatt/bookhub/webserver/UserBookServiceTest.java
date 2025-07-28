package com.evaitcsmatt.bookhub.webserver;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;
import com.evaitcsmatt.bookhub.webserver.service.UserBookServiceImpl;

public class UserBookServiceTest {
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private UserBookServiceImpl userBookServiceImpl;
	
	private BookDto resultBookDto;
	
	private BookDto editedBookDto;
	
	private PostNewBook postNewBook;
	
	private Book afterBook;
	
	private Book book;
	
	private AutoCloseable closeable;
	
	@BeforeEach
	private void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		
		postNewBook = new PostNewBook(
				"Clean Code", 
				"Robert C. Martin", 
				"Non-Fiction", 
				LocalDate.of(2008, 8, 1),
				(byte) 4);
		
		resultBookDto = new BookDto(
				1,
				"Clean Code", 
				"Robert C. Martin", 
				"Non-Fiction", 
				(byte) 4);
		
		editedBookDto = new BookDto(
				1,
				"Clean Code", 
				"Robert C. Martin", 
				"Programming", 
				(byte) 4);
		
		book = new Book(
				0, 
				"Clean Code", 
				"Robert C. Martin", 
				LocalDate.of(2008, 8, 1), 
				"Non-Fiction", 
				(byte) 4);
		
		afterBook = new Book(
				1, 
				"Clean Code", 
				"Robert C. Martin", 
				LocalDate.of(2008, 8, 1), 
				"Non-Fiction", 
				(byte) 4);
	}
	
	@AfterEach
	private void trash() throws Exception {
		closeable.close();
		postNewBook = null;
		resultBookDto = null;
		editedBookDto = null;
	}
	
	@Test
	void UserBookServiceImpl_addBook_ShouldSucceed() {
		when(bookRepository.save(book)).thenReturn(afterBook);
		BookDto actualBookDto = userBookServiceImpl.createBook(postNewBook);
		Assertions.assertEquals(resultBookDto, actualBookDto);
	}
}
