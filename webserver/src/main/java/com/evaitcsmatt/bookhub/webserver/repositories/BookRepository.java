package com.evaitcsmatt.bookhub.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.evaitcsmatt.bookhub.webserver.entities.Book;

//JPA Repositories
//CrudRepository
//ListCrudRepository
//PagingAndSortingRepository
//JpaRepository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
