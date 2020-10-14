package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	public static void main(String[] args) {
		Main.bootstrap(); 
	}

	public static void bootstrap() {

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
		
		Model model = new Model(conn);
		
//		try {
//			ResultSet set = model.index();
//			while (set.next()) {
//				System.out.println(set.getString("firstname"));
//				System.out.println(set.getString("lastname"));
//				System.out.println(set.getString("team"));
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("Could not execute query");
//			e.printStackTrace();
//		}
		
		try {
			ResultSet set = model.show("2");
			if (set.next()) {
				System.out.println(set.getString("firstname"));
				System.out.println(set.getString("lastname"));
				System.out.println(set.getString("team"));
			}
		} catch (SQLException e) {
			System.out.println("Could not execute query");
			e.printStackTrace();
		}
		
	}
}
