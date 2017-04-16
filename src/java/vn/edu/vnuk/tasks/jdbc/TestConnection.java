package vn.edu.vnuk.tasks.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import vn.edu.vnuk.tasks.jdbc.ConnectionFactory;

public class TestConnection {
	
	public static void main(String args[]) throws SQLException{
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Connection is open!");
		connection.close();
	}

}
