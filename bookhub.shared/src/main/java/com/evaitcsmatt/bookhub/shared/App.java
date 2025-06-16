package com.evaitcsmatt.bookhub.shared;

import java.time.LocalDate;

import com.evaitcsmatt.bookhub.shared.entities.Book;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setPublishDate(LocalDate.of(2008, 8, 1));
        book1.setGenre("Non Fiction");
        book1.setPrice(59.99f);
        book1.setRating((byte) 4);
        System.out.println(book1);
        book1.displayBook();
    }
}
