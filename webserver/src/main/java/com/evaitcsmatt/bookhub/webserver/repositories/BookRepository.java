package com.evaitcsmatt.bookhub.webserver.repositories;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evaitcsmatt.bookhub.webserver.dto.GenreStatsProjection;
import com.evaitcsmatt.bookhub.webserver.entities.Book;

//JPA Repositories
//CrudRepository
//ListCrudRepository
//PagingAndSortingRepository
//JpaRepository
public interface BookRepository extends JpaRepository<Book, Integer> {
	long countByTitle(String title);
	List<Book> findAllByAuthor(String author);
	@Query("SELECT b.genre as genre, COUNT(b.id) as count FROM Book b GROUP BY b.genre")
	List<GenreStatsProjection> getStatsOfLibrary();
	Optional<Book> findByTitle(String title);
	List<Book> findAllByGenreIgnoreCase(String genre);
}
