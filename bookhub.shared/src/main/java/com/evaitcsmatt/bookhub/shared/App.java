package com.evaitcsmatt.bookhub.shared;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import com.evaitcsmatt.bookhub.shared.entities.Book;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);
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
        Book book2 = new Book(
        		2, 
        		"The Well-Grounded Java Developer", 
        		"Ben", 
        		LocalDate.of(2023, 2, 7), 
        		39.99f, 
        		"Non Fiction", 
        		(byte)4
        		);
        book2.displayBook();
        
        System.out.println("Please enter the first number!");
        int num = scanner.nextInt();
        System.out.println("Please enter the second number!");
        int num2 = scanner.nextInt();
        try {
        	int results = num/num2;
        } catch (ArithmeticException e) {
			System.out.println("You can't divide by zero");
		} finally {
			
		}
        //accessFile();
    }
    
    private static void accessFile() throws IOException {
    	FileReader fReader = new FileReader("bad_file.txt");
    }
}
