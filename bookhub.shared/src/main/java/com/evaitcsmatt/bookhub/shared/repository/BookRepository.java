package com.evaitcsmatt.bookhub.shared.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.evaitcsmatt.bookhub.shared.entities.Book;

public class BookRepository {
	private Connection connection;
	
	public BookRepository() {
		connection = DatabaseConnection.getInstance().getConnection();
	}
	
	public Book save(Book book) {
		String sqlString = "INSERT INTO books (title, author, publish_date, price, genre, rating) " +
				"VALUE (?, ?, ?, ?, ?, ?)";
		String[] returnedColumnStrings = { "id", "date_added" };
		try (PreparedStatement statement = connection.prepareStatement(sqlString, returnedColumnStrings)) {
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setDate(3, Date.valueOf(book.getPublishDate()));
			statement.setFloat(4, book.getPrice());
			statement.setString(5, book.getGenre());
			statement.setByte(6, book.getRating());
			int rows = statement.executeUpdate();
			if (rows == 0) throw new SQLException("Creating book failed, no rows affected.");
			try (var generatedKeys = statement.getGeneratedKeys()) {
			    
				if(generatedKeys.next()) {
					int bookId = generatedKeys.getInt(1);
					book.setId(bookId);
					String fetchTimestampSql = "SELECT date_added FROM books WHERE id = ?";
	                try (PreparedStatement fetchStmt = connection.prepareStatement(fetchTimestampSql)) {
	                    fetchStmt.setInt(1, bookId);
	                    try (var rs = fetchStmt.executeQuery()) {
	                        if (rs.next()) {
	                            book.setDateAdded(rs.getTimestamp("date_added").toLocalDateTime());
	                        }
	                    }
	                }
				}
			}
			return book;
		} catch (SQLException e) {
			System.err.println("Error has occurred while saving: " + e.getMessage());
		}
		return null;
	}
	
	public List<Book> findAll() throws SQLException {
		List<Book> books = new ArrayList<Book>();
		String sqlString = "SELECT * FROM books";
		try (Statement statement = connection.createStatement();
				ResultSet bookSet = statement.executeQuery(sqlString)) {
			while (bookSet.next()) {
				Book book = new Book();
				book.setId(bookSet.getInt(1));
				book.setTitle(bookSet.getString(2));
				book.setAuthor(bookSet.getString(3));
				book.setPublishDate(bookSet.getDate(4).toLocalDate());
				book.setPrice(bookSet.getFloat(5));
				book.setGenre(bookSet.getString(6));
				book.setRating(bookSet.getByte(7));
				book.setDateAdded(bookSet.getTimestamp(8).toLocalDateTime());
				books.add(book);
				book = null;
			}
			return books;
		}
	}
}
