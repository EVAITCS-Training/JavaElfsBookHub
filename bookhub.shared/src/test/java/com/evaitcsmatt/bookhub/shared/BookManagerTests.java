package com.evaitcsmatt.bookhub.shared;

import java.lang.reflect.Field;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.evaitcsmatt.bookhub.shared.entities.Book;
import com.evaitcsmatt.bookhub.shared.managers.BookManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookManagerTests {
	private BookManager bookManager;
	
	@BeforeEach
	private void setUp() {
		bookManager = new BookManager();
		bookManager.addBook(
	            "To Kill a Mockingbird",
	            "Harper Lee",
	            LocalDate.of(1960, 7, 11),
	            "Fiction",
	            (byte) 5,
	            12.99f
	        );
	        
		bookManager.addBook(
	            "1984",
	            "George Orwell",
	            LocalDate.of(1949, 6, 8),
	            "Dystopian Fiction",
	            (byte) 5,
	            13.99f
	        );
	        
        bookManager.addBook(
            "Pride and Prejudice",
            "Jane Austen",
            LocalDate.of(1813, 1, 28),
            "Romance",
            (byte) 4,
            11.50f
        );
        
        // Modern Fiction
        bookManager.addBook(
            "The Alchemist",
            "Paulo Coelho",
            LocalDate.of(1988, 1, 1),
            "Adventure",
            (byte) 4,
            14.95f
        );
        
        bookManager.addBook(
            "Where the Crawdads Sing",
            "Delia Owens",
            LocalDate.of(2018, 8, 14),
            "Mystery",
            (byte) 4,
            16.99f
        );
        
        // Science Fiction & Fantasy
        bookManager.addBook(
            "Dune",
            "Frank Herbert",
            LocalDate.of(1965, 8, 1),
            "Science Fiction",
            (byte) 5,
            18.99f
        );
        
        bookManager.addBook(
            "The Hobbit",
            "J.R.R. Tolkien",
            LocalDate.of(1937, 9, 21),
            "Fantasy",
            (byte) 5,
            15.99f
        );
        
        // Non-Fiction
        bookManager.addBook(
            "Sapiens",
            "Yuval Noah Harari",
            LocalDate.of(2011, 1, 1),
            "History",
            (byte) 4,
            19.99f
        );
        
        bookManager.addBook(
            "Atomic Habits",
            "James Clear",
            LocalDate.of(2018, 10, 16),
            "Self-Help",
            (byte) 4,
            17.99f
        );
        
        // Technical Books
        bookManager.addBook(
            "Clean Code",
            "Robert C. Martin",
            LocalDate.of(2008, 8, 1),
            "Programming",
            (byte) 5,
            44.99f
        );
        
	}
	
	@AfterEach
	private void cleanUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		bookManager = null;
	}
	
	@Test
	@DisplayName("Should give acturate size of array when tested!")
	void testGetAllBooksSuccessTest() {
		assertEquals(10, bookManager.getAllBooks().size());
	}
	
	@Test
	@DisplayName("Should update the book in the Array!")
	void testUpdateBookShouldUpdateTheBook() {
		Book oldBook = bookManager.getBookById(13);
		
		Book expectedBook = new Book(oldBook.getId(), oldBook.getDateAdded());
		expectedBook.setTitle("Dirty Code");
		expectedBook.setAuthor("Mohammad Ahmadi");
		expectedBook.setGenre("Non-Fiction");
		expectedBook.setPublishDate(LocalDate.of(2025, 06 , 23));
		expectedBook.setRating((byte)0);
		
		assertTrue(bookManager.updateBook(expectedBook));
		Book updateBook = bookManager.getBookById(13);
		assertEquals(expectedBook.getTitle(), updateBook.getTitle());
		assertEquals(expectedBook.getAuthor(), updateBook.getAuthor());
	}

}
