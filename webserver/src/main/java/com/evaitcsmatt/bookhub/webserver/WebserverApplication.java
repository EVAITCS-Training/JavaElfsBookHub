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

import com.evaitcsmatt.bookhub.webserver.dto.PostNewBook;
import com.evaitcsmatt.bookhub.webserver.dto.PostNewBookAdmin;
import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;
import com.evaitcsmatt.bookhub.webserver.utils.BookServiceFactory;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class WebserverApplication implements CommandLineRunner {
	//private static final Logger LOGGER = LoggerFactory.getLogger(WebserverApplication.class);
	
	
	@Autowired
	private BookServiceFactory bookServiceFactory;

	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
