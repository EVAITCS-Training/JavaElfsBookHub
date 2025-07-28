package com.evaitcsmatt.bookhub.webserver;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.evaitcsmatt.bookhub.webserver.entities.Book;
import com.evaitcsmatt.bookhub.webserver.repositories.BookRepository;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Transactional
@AutoConfigureTestEntityManager
public class WebserverIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	private static final Network NETWORK = Network.newNetwork();
	
	@Container
	private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0")
		.withDatabaseName("testdb")
		.withUsername("test")
		.withPassword("test");
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
		registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
		registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
		registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
	    registry.add("spring.jpa.show-sql", () -> "true");
	    registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.MySQLDialect");
	}
	
	@Test
	void shouldSaveAndFindBook() {
		Book book = new Book(
				0, 
				"Clean Code", 
				"Robert C. Martin", 
				LocalDate.of(2008, 8, 1), 
				"Non-Fiction", 
				(byte) 4);
		
		Book actualBook = bookRepository.save(book);
		entityManager.flush();
		
		
		Optional<Book> foundBookOptional = bookRepository.findById(actualBook.getId());
		assertThat(foundBookOptional).isPresent();
		assertThat(foundBookOptional.get().getTitle()).isEqualTo("Clean Code");
	}
}
