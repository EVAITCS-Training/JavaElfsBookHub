package com.evaitcsmatt.bookhub.shared.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.evaitcsmatt.bookhub.shared.exceptions.BookInputException;

public class Book implements Comparable<Book>, Comparator<Book> {
	//Encapsulation
	//Control the flow of Data, coming in and out of our classes
	//private field variables
	//public getters and setters
	// number primitive data types
	//byte(8 bits)(-128 - 127), short(16 bits)(-32,768 - 32,767), int(32 bits), long(64 bits)
	// decimal primitive data types
	// double, float
	private int id;
	private String title;
	private String author;
	private LocalDate publishDate;
	private float price;
	private String genre;
	private byte rating;
	private LocalDateTime dateAdded;
	
	private List<Review> reviews;
	
	
	//Constructor
	//Is a special method that initializes the instance object from the class object
	// it called in conjunction with the new keyword
	public Book() {
		this.dateAdded = LocalDateTime.now();
		this.reviews = new ArrayList<Review>();
	}
	
	public Book(int id, LocalDateTime dateAdded) {
		this.id = id;
		this.dateAdded = dateAdded;
		this.reviews = new ArrayList<Review>();
	}
	
	public Book(
			String title,
			String author, 
			LocalDate publishDate, 
			float price, 
			String genre, 
			byte rating
			) {
		if(title == null || title.trim().isEmpty()) {
			throw new BookInputException("Title needs to be filled out!");
		}
		if(author == null || author.trim().isEmpty()) {
			throw new BookInputException("Author needs to be filled out!");
		}
		if(publishDate == null) {
			throw new BookInputException("Have to have a publish date whether in future or past!");
		}
		if(genre == null|| genre.trim().isEmpty()) {
			throw new BookInputException("Genre is required to be filled out!");
		}
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.price = price;
		this.genre = genre;
		this.rating = rating;
		this.reviews = new ArrayList<Review>();
		dateAdded = LocalDateTime.now();
	}
	
	//Getters/Setters
	/*
	 * These are used to access the field variables in the instance object
	 *  */
	
	/**
	 * 	This is a method to retrieve the id of the book
	 * @return int
	 *  */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title == null || title.trim().isEmpty()) {
			throw new BookInputException("Title needs to be filled out!");
		}
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if(author == null || author.trim().isEmpty()) {
			throw new BookInputException("Author needs to be filled out!");
		}
		this.author = author;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
	    if(price < 0) {
	        throw new BookInputException("Price cannot be negative!");
	    }
	    this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if(genre == null|| genre.trim().isEmpty()) {
			throw new BookInputException("Genre is required to be filled out!");
		}
		this.genre = genre;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}
	
	public LocalDateTime getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void displayBook() {
		System.out.println("Book Information:\n" + 
				"Id: " + getId() +
				"\nTitle: " + getTitle() +
				"\nAuthor: " + getAuthor() +
				"\nPublish Date: " + getPublishDate() +
				"\nPrice: $ " + getPrice() +
				"\nGenre: " + getGenre() +
				"\nCurrent Rating: " + getRating() + " Stars"
				);
	}

	@Override
	public String toString() {
		return "Book [id=" + id +
				", title=" + title + 
				", author=" + author + 
				", publishDate=" + publishDate
				+ ", price=" + price + 
				", genre=" + genre + 
				", rating=" + rating + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, genre, id, price, publishDate, rating, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Book))
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(genre, other.genre) && id == other.id
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(publishDate, other.publishDate) && rating == other.rating
				&& Objects.equals(title, other.title);
	}

	@Override
	public int compareTo(Book o) {
	    return this.title.compareToIgnoreCase(o.title);
	}

	@Override
	public int compare(Book o1, Book o2) {
	    // Compare by title first
	    int titleComparison = o1.title.compareToIgnoreCase(o2.title);
	    if (titleComparison != 0) {
	        return titleComparison;
	    }
	    
	    // If titles are equal, compare by author
	    int authorComparison = o1.author.compareToIgnoreCase(o2.author);
	    if (authorComparison != 0) {
	        return authorComparison;
	    }
	    
	    // If authors are equal, compare by publish date
	    return o1.publishDate.compareTo(o2.publishDate);
	}
	
	
}
