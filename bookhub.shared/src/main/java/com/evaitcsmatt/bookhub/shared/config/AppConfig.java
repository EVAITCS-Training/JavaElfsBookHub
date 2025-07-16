package com.evaitcsmatt.bookhub.shared.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evaitcsmatt.bookhub.shared.managers.BookManager;
import com.evaitcsmatt.bookhub.shared.repository.BookRepository;
import com.evaitcsmatt.bookhub.shared.ui.BookHubConsole;

@Configuration
public class AppConfig {
	
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
	
	@Bean
	public BookRepository bookRepository() {
		return new BookRepository();
	}
	
	@Bean
	public BookManager bookManager(BookRepository bookRepository) {
		return new BookManager(bookRepository);
	}
	
	@Bean
	public BookHubConsole bookHubConsole(BookManager bookManager, Scanner scanner) {
		return new BookHubConsole(bookManager, scanner);
	}
}
