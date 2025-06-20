package com.evaitcsmatt.bookhub.shared.managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.evaitcsmatt.bookhub.shared.entities.Book;
import com.evaitcsmatt.bookhub.shared.exceptions.BookNotFoundException;

public class BookManager {
	private List<Book> books;
	
	public BookManager() {
		this.books = new ArrayList<Book>();
	}

	public void addBook(
			String title, 
			String author, 
			LocalDate publishDate, 
			String genre, 
			byte rating, 
			float price
			) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublishDate(publishDate);
		book.setGenre(genre);
		book.setRating(rating);
		book.setPrice(price);
		book.displayBook();
		books.add(book);
	}
	
	public void addBook(
			String title, 
			String author, 
			LocalDate publishDate, 
			String genre
			) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublishDate(publishDate);
		book.setGenre(genre);
		book.setRating((byte)0);
		book.setPrice(0.0f);
		book.displayBook();
		books.add(book);
	}
	
	public void addBook(
			String title, 
			String author, 
			LocalDate publishDate, 
			String genre, 
			byte rating
			) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublishDate(publishDate);
		book.setGenre(genre);
		book.setRating(rating);
		book.setPrice(0.0f);
		book.displayBook();
		books.add(book);
	}
	
	public List<Book> getAllBooks() {
		return books;
	}
	
	public Book getBookByTitle(String title) {
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equalsIgnoreCase(title)) {
				return books.get(i);
			}
		}
		return null;
	}
	
	public List<Book> getBooksByAuthor(String author) {
		List<Book> booksByAuthorBooks = new ArrayList<Book>();
		for (Book book : books) {
			if(book.getAuthor().equalsIgnoreCase(author) || book.getAuthor().contains(author)) {
				booksByAuthorBooks.add(book);
			}
		}
		return booksByAuthorBooks;
	}
	
	public List<Book> getBooksByGenre(String genre) {
		return books.stream()//converts the collection into a stream object
				.filter(book -> book.getGenre().equalsIgnoreCase(genre)) //Intermediary operation
				.toList(); //Terminal Operator
	}
	
	public Map<String, Long> getBookGenreStatistics() {
		return books.stream()
				.collect(Collectors.groupingBy(
						Book::getGenre,
						Collectors.counting()
						));
	}
	
	public List<Book> getSortedBooksByPublishedDate() {
		return books.stream()
				.sorted((book1, book2) -> book1.getPublishDate().compareTo(book2.getPublishDate()))
				.toList();
	}
	
	public boolean updateBook(Book book) {
		Book oldBook = books.stream()
				.filter(b1 -> b1.getId() == book.getId())
				.findAny()
				.orElseThrow(() -> new BookNotFoundException("Book was not found!"));
		if(oldBook.equals(book)) {
			return false;
		}
		books.replaceAll(b -> b.getId() == book.getId() ? book : b);
		return true;
	}
	
	public boolean updateRating(int bookId, byte rating) {
		Book book = books.stream()
				.filter(b -> b.getId() == bookId)
				.findFirst()
				.orElseThrow(() -> new BookNotFoundException("Book was not found!"));
		book.setRating(rating);
		return true;
	}
	
	public boolean deleteBookById(int bookId) {
		return books.removeIf(b -> b.getId() == bookId);
	}
}
