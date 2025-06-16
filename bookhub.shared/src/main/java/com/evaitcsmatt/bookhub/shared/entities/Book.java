package com.evaitcsmatt.bookhub.shared.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Book extends Object {
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
	
	//Constructor
	//Is a special method that initializes the instance object from the class object
	// it called in conjunction with the new keyword
	public Book() {}
	
	public Book(
			int id,
			String title,
			String author, 
			LocalDate publishDate, 
			float price, 
			String genre, 
			byte rating
			) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.price = price;
		this.genre = genre;
		this.rating = rating;
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
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
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
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}
	
	public void displayBook() {
		System.out.println("Book Information:\n" + 
				"Title: " + getTitle() +
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
	
	
}
