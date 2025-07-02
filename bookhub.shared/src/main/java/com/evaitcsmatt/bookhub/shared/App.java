package com.evaitcsmatt.bookhub.shared;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    	Scanner scanner = new Scanner(System.in);
    	BookRepository bookRepository = new BookRepository();
    	BookManager bookManager = new BookManager(bookRepository);
    	BookHubConsole console = new BookHubConsole(bookManager, scanner);
    	console.start();
    }
    
    private static void accessFile() throws IOException {
    	FileReader fReader = new FileReader("bad_file.txt");
    }
}
