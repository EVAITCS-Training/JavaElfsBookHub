package com.evaitcsmatt.bookhub.webserver.utils;

import com.evaitcsmatt.bookhub.webserver.dto.AdminBookDto;
import com.evaitcsmatt.bookhub.webserver.dto.BookDto;
import com.evaitcsmatt.bookhub.webserver.entities.Book;

public class BookMapper {
	private BookMapper() {}
	
	public static BookDto bookToBookDto(Book book) {
		return new BookDto(
				book.getId(), 
				book.getTitle(), 
				book.getAuthor(), 
				book.getGenre(), 
				book.getRating());
	}
	
	
	public static AdminBookDto bookToAdminBookDto(Book book) {
		return new AdminBookDto(
				book.getId(), 
				book.getTitle(), 
				book.getAuthor(), 
				book.getGenre(), 
				book.getRating(), 
				book.getPrice(), 
				book.getPublishDate());
	}
}
