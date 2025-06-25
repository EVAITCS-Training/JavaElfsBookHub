package com.evaitcsmatt.bookhub.shared;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.evaitcsmatt.bookhub.shared.entities.Book;
import com.evaitcsmatt.bookhub.shared.example.Animal;
import com.evaitcsmatt.bookhub.shared.managers.BookManager;
import com.evaitcsmatt.bookhub.shared.ui.BookHubConsole;
import com.evaitcsmatt.bookhub.shared.utils.BookFileHandler;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
//    	Scanner scanner = new Scanner(System.in);
//    	BookManager bookManager = new BookManager();
//    	System.out.println("Beginning to add book");
//    	bookManager.addBook("Gantz", "Hiroya Oku", LocalDate.of(2000, 6, 1), "Manga");
//    	System.out.println("Book added");
//    	bookManager.addBook("Clean Code", "Robert C. Martin", LocalDate.of(2008, 8, 1), "Non Fiction",(byte) 4);
//    	System.out.println("Book Added");
//    	
//    	System.out.println(bookManager.getBookGenreStatistics());
//        Book book1 = new Book();
//        book1.setTitle("Clean Code");
//        book1.setAuthor("Robert C. Martin");
//        book1.setPublishDate(LocalDate.of(2008, 8, 1));
//        book1.setGenre("Non Fiction");
//        book1.setPrice(59.99f);
//        book1.setRating((byte) 4);
//        System.out.println(book1);
//        book1.displayBook();
//        Book book2 = new Book(
//        		"The Well-Grounded Java Developer", 
//        		"Ben", 
//        		LocalDate.of(2023, 2, 7), 
//        		39.99f, 
//        		"Non Fiction", 
//        		(byte)4
//        		);
//        book2.displayBook();
//        
//        String strNameString = new String("Robert C. Martin");
//        //Can be used in any minus threading
//        //Not Thread Safe
//        StringBuilder sBuilder = new StringBuilder();
//        //Thread Safe
//        StringBuffer sBuffer = new StringBuffer();
//        
//        System.out.println(book1.getAuthor() == strNameString);
        
//        System.out.println("Please enter the first number!");
//        int num = scanner.nextInt();
//        System.out.println("Please enter the second number!");
//        int num2 = scanner.nextInt();
//        try {
//        	int results = num/num2;
//        } catch (ArithmeticException e) {
//			System.out.println("You can't divide by zero");
//		} finally {
//			
//		}
        //accessFile();
    	
//    	Animal dogAnimal = new Animal() {
//			
//			@Override
//			public void speak() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void eat() {
//				// TODO Auto-generated method stub
//				
//			}
//		};
    	
    	BookHubConsole console = new BookHubConsole();
    	console.start();
    	
//    	List<Book> books = new ArrayList<Book>();
//    	books.add(new Book("To Kill a Mockingbird",
//            "Harper Lee",
//            LocalDate.of(1960, 7, 11),
//            12.99f,
//            "Fiction",
//            (byte) 5
//            ));
//    	books.add(new Book("To Kill a Mockingbird",
//            "Harper Lee",
//            LocalDate.of(1960, 7, 11),
//            12.99f,
//            "Fiction",
//            (byte) 5
//            ));
//    	BookFileHandler.saveBooks(books);
//    	
//    	List<Book> newList = BookFileHandler.loadBooks();
//    	books.forEach(System.out::println);
//    	System.out.println(newList);
//    	newList.forEach(System.out::println);
    }
    
    private static void accessFile() throws IOException {
    	FileReader fReader = new FileReader("bad_file.txt");
    }
}
