package com.evaitcsmatt.bookhub.shared.managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.evaitcsmatt.bookhub.shared.entities.Book;

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
		return books.stream()
				.filter(book -> book.getGenre().equalsIgnoreCase(genre))
				.toList();
	}
	
	public Map<String, Long> getBookGenreStatistics() {
		return books.stream()
				.collect(Collectors.groupingBy(
						Book::getGenre,
						Collectors.counting()
						));
	}
}
