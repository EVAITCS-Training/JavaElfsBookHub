package com.evaitcsmatt.bookhub.shared.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static DatabaseConnection instanceConnection;
	private Connection connection;
	
	private DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bookhubdb",
					"root",
					"Gudmord92!"
					);
		} catch (Exception e) {
			System.err.println("Connection Failed: " + e.getMessage());
		}
	}
	
	public static DatabaseConnection getInstance() {
		if(instanceConnection == null) {
			instanceConnection = new DatabaseConnection();
		}
		return instanceConnection;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
