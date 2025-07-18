package com.evaitcsmatt.bookhub.webserver;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class WebserverApplication implements CommandLineRunner {
	//private static final Logger LOGGER = LoggerFactory.getLogger(WebserverApplication.class);
	
	
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		log.info(String.valueOf(bookRepository.countByTitle("Dune")));
		log.info(List.of(bookRepository.findAllByAuthor("Robert C. Martin")).toString());
		log.info(bookRepository.getStatsOfLibrary().toString());
		log.info(bookRepository.findAllByGenreIgnoreCase("fiction").toString());
	}

}
