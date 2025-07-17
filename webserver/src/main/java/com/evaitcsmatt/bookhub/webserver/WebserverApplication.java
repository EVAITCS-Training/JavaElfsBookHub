package com.evaitcsmatt.bookhub.webserver;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;

@SpringBootApplication
public class WebserverApplication implements CommandLineRunner {
	
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book();
		book.setTitle("IT");
		book.setAuthor("Stephan King");
		book.setGenre("Horror");
		book.setPrice(15.99f);
		book.setRating((byte)5);
		book.setPublishDate(LocalDate.of(1986, 9, 15));
		bookRepository.save(book);
		bookRepository.findAll(Pageable.unpaged()).forEach(System.out::println);;
		
	}

}
