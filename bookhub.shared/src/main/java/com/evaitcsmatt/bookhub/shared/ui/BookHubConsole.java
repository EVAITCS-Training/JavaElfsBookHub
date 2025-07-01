package com.evaitcsmatt.bookhub.shared.ui;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import com.evaitcsmatt.bookhub.shared.entities.Book;
import com.evaitcsmatt.bookhub.shared.managers.BookManager;

public class BookHubConsole {
	private BookManager bookManager;
	private Scanner scanner;
	
	public BookHubConsole(BookManager bookManager, Scanner scanner) {
		this.bookManager = bookManager;
		this.scanner = scanner;
	}
	
	public void start() {
		System.out.println("Welcome to Bookhub!");
		
		while (true) {
			displayMenu();
			int choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				addBook();
				break;
			case 2:
				getAllBooks();
				break;
			case 8:
				updateBook();
				break;
			case 10:
				deleteById();
				break;
			case 11:
				System.out.println("Goodbye!");
				return;
			default:
				System.out.println("Invalid Choice!");
			}
		}
	}
	
	private void displayMenu() {
		System.out.println("""
				+---------------------------+
				|		MAIN MENU			|
				+---------------------------+
				|1. Add a Book				|
				|2. Get all Books			|
				|3. Get Book by Title		|
				|4. Get Books by Author		|
				|5. Get Books by Genre		|
				|6. Get Books Genre Stats	|
				|7. Get Sorted Book List	|
				|8. Update a Book			|
				|9. Update Rating of Book	|
				|10. Delete a Book			|
				|11. Exit					|
				+---------------------------+
				"""); // "" single line
		// """  """
	}
	
	private void displayBookIds() {
		System.out.println("Current Book Ids");
		bookManager.getAllBooks().forEach(book -> {
			System.out.printf("Id: %d, Title: %s", book.getId(), book.getTitle());
		});
	}
	
	private void addBook() {
		scanner.nextLine();
		System.out.println("Enter title: ");
		String titleString = scanner.nextLine();
		
		System.out.println("Enter author: ");
		String authorString = scanner.nextLine();
		
		System.out.println("Enter Publish Date(YYYY-MM-DD): ");
		LocalDate publishDate = LocalDate.parse(scanner.nextLine());
		
		System.out.println("Enter genre: ");
		String genString = scanner.nextLine();
		
		System.out.println("Enter Rating(optional)");
		String ratingOptional = scanner.nextLine();
		
		System.out.println("Enter price(optional): ");
		String priceOptional = scanner.nextLine();
		
		if(!ratingOptional.isBlank()) {
			if(!priceOptional.isBlank()) {
				bookManager.addBook(
						titleString, 
						authorString, 
						publishDate, 
						genString, 
						Byte.parseByte(ratingOptional), 
						Float.parseFloat(priceOptional));
				return;
			}
			bookManager.addBook(
					titleString, 
					authorString, 
					publishDate, 
					genString, 
					Byte.parseByte(ratingOptional));
			return;
		}
		bookManager.addBook(
				titleString, 
				authorString, 
				publishDate, 
				genString);
	}
	
	private void getAllBooks() {
		if(bookManager.getAllBooks().isEmpty()) {
			System.out.println("The library is empty you donut!");
			return;
		}
		
		System.out.println("Your Books");
		bookManager.getAllBooks().forEach(Book::displayBook);
	}
	
	private void updateBook() {
		displayBookIds();
		System.out.println("Please choose an Id: ");
		
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		try {
			Book existingBook = bookManager.getBookById(choice);
			System.out.println("Current Book Information");
			existingBook.displayBook();
			
			System.out.println("Enter new title (current: ".concat(existingBook.getTitle()).concat(" ): "));
			String newTitleString = scanner.nextLine();
			if(newTitleString.isBlank()) newTitleString = existingBook.getTitle();
			
			System.out.println("Enter new author (current: ".concat(existingBook.getAuthor()).concat(" ): "));
			String newAuthorString = scanner.nextLine();
			if(newAuthorString.isBlank()) newAuthorString = existingBook.getAuthor();
			
			System.out.println("Enter new publish date (current: ".concat(existingBook.getPublishDate().toString()).concat(" ) [YYYY-MM-DD]: "));
			String dateInputString = scanner.nextLine();
			LocalDate newDate = dateInputString.isBlank() ? existingBook.getPublishDate() : LocalDate.parse(dateInputString);
			
			System.out.println("Enter new genre (current: ".concat(existingBook.getGenre()).concat(" ): "));
			String newGenreString = scanner.nextLine();
			if(newGenreString.isBlank()) newGenreString = existingBook.getGenre();
			
			System.out.println("Enter new price (current: " + existingBook.getPrice() + " ): ");
			String newPriceString = scanner.nextLine();
			float newPrice = newPriceString.isBlank() ? existingBook.getPrice() : Float.parseFloat(newPriceString);
			
			Book updatedBook = new Book(existingBook.getId(), existingBook.getDateAdded());
			updatedBook.setTitle(newTitleString);
			updatedBook.setAuthor(newAuthorString);
			updatedBook.setGenre(newGenreString);
			updatedBook.setPrice(newPrice);
			updatedBook.setPublishDate(newDate);
			updatedBook.setRating(existingBook.getRating());
			
			if(bookManager.updateBook(updatedBook)) {
				System.out.println("Book updated successfully!");
			} else {
				System.out.println("No changes made.");
			}
		} catch (Exception e) {
			System.err.println("Book not found or invalid input!");
		}
				
	}
	
	private void deleteById() {
		displayBookIds();
		System.out.println("Please choose an Id: ");
		
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		if(bookManager.deleteBookById(choice)) {
			System.out.println("Book successfully deleted!");
		} else {
			System.err.println("Invalid Book Choice");
		}
	}
}
