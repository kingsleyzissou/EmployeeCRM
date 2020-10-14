package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	public static void main(String[] args) {
		Main.run(); 
	}

	public static void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		// Create a ResultSet & Display Table contents
		// (Previously Create a table)
		try {

		    // Insert code here (from lecture notes)
//			Statement s = conn.createStatement ();
//			 s.executeUpdate ("DROP TABLE IF EXISTS animal");
//			 s.executeUpdate (
//			 "CREATE TABLE animal ("
//			 + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
//			 + "PRIMARY KEY (id),"
//			 + "name CHAR(40), category CHAR(40))");
//			 int count;
//			 count = s.executeUpdate (
//			  "INSERT INTO animal (name, category)"
//			  + " VALUES"
//			  + "('snake', 'reptile'),"
//			  + "('frog', 'amphibian'),"
//			  + "('tuna', 'fish'),"
//			  + "('racoon', 'mammal')");
//			  s.close ();
//			  System.out.println (count +
//			  " rows were inserted");
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT id, name, category " +
			 "FROM animal");
			ResultSet rs = s.getResultSet ();
			int count = 0;
			while (rs.next ()) {
				 int idVal = rs.getInt ("id");
				 String nameVal = rs.getString ("name");
				 String catVal = rs.getString ("category");
				 System.out.println (
				 "id = " + idVal
				 + ", name = " + nameVal
				 + ", category = " + catVal);
				 ++count;
			}
			rs.close ();
			s.close ();
			System.out.println (count + " rows were retrieved");
		    System.out.println("ResultSet printed");

	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the resultSet");
			e.printStackTrace();
			return;
		}
		
	}
}
