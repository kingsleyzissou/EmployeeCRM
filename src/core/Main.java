package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		
		
		
		
		
		try {
			new View(model.all(), model);
			// It creates and displays the table
//		    JTable table = new JTable(buildTableModel(set));

		    // Closes the Connection

//		    JOptionPane.showMessageDialog(null, new JScrollPane(table));
//			while (set.next()) {
//				System.out.println(set.getString("firstname"));
//				System.out.println(set.getString("lastname"));
//				System.out.println(set.getString("team"));
//			}
			
		} catch (SQLException e) {
			System.out.println("Could not execute query");
			e.printStackTrace();
		}
		
//		try {
//			ResultSet set = model.show("2");
//			if (set.next()) {
//				System.out.println(set.getString("firstname"));
//				System.out.println(set.getString("lastname"));
//				System.out.println(set.getString("team"));
//			}
//		} catch (SQLException e) {
//			System.out.println("Could not execute query");
//			e.printStackTrace();
//		}
		
	}
}