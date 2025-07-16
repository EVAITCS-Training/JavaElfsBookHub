package com.evaitcsmatt.bookhub.shared;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.evaitcsmatt.bookhub.shared.config.AppConfig;
import com.evaitcsmatt.bookhub.shared.entities.Book;
import com.evaitcsmatt.bookhub.shared.example.Animal;
import com.evaitcsmatt.bookhub.shared.managers.BookManager;
import com.evaitcsmatt.bookhub.shared.repository.BookRepository;
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
    	//ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//    	Scanner scanner = new Scanner(System.in);
//    	BookRepository bookRepository = new BookRepository();
//    	BookManager bookManager = new BookManager(bookRepository);
//    	BookHubConsole console = new BookHubConsole(bookManager, scanner);
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	BookHubConsole console = context.getBean(BookHubConsole.class);
    	console.start();
    }
    
    private static void accessFile() throws IOException {
    	FileReader fReader = new FileReader("bad_file.txt");
    }
}
