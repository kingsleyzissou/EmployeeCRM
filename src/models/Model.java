package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import core.DBConnection;

public class Model {
	
	private Connection connection = null;
	
	public Model() {
		try {
			this.connection = DBConnection.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
	}

	public ArrayList<Player> all() throws SQLException {
		ArrayList<Player> list = new ArrayList<>();
		Statement stmt = this.connection.createStatement();
		stmt.executeQuery("SELECT * FROM players");
		ResultSet res = stmt.getResultSet();
		while(res.next()) {
			Player player = new Player(
				res.getInt("id"),
				res.getString("firstname"),
				res.getString("lastname"),
				res.getInt("jersey"),
				res.getString("position"),
				res.getString("nationality"),
				res.getString("team"),
				res.getString("dob")
			);
			list.add(player);
		}
		return list;
	}
	
	public Player show(String id) throws SQLException {
		Statement stmt = this.connection.createStatement();
		stmt.executeQuery("SELECT * FROM players WHERE id = " + id);
		ResultSet res = stmt.getResultSet();
		if (res.next()) {
			return new Player(
				res.getInt("id"),
				res.getString("firstname"),
				res.getString("lastname"),
				res.getInt("jersey"),
				res.getString("position"),
				res.getString("nationality"),
				res.getString("team"),
				res.getString("dob")
			);
		}
		System.out.println("No results found");
		return null;
	}
	
	public void create(Player p) throws SQLException {
		String sql = "INSERT INTO players ("
				+ "firstname, lastname, jersey,"
				+ "nationality, position, team, dob"
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setString(1, p.name);
		stmt.setString(2, p.lastname);
		stmt.setInt(3, p.jersey);
		stmt.setString(4, p.country);
		stmt.setString(5, p.position);
		stmt.setString(6, p.team);
		stmt.setString(7, p.dob);
		stmt.executeUpdate();
	}
	
	public void update(Player p) throws SQLException {
		String sql = "UPDATE players SET "
				+ "firstname=?, "
				+ "lastname=?, "
				+ "jersey=?, "
				+ "nationality=?, "
				+ "position=?, "
				+ "team=?, "
				+ "dob=? "
				+ "WHERE id=?";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setString(1, p.name);
		stmt.setString(2, p.lastname);
		stmt.setInt(3, p.jersey);
		stmt.setString(4, p.country);
		stmt.setString(5, p.position);
		stmt.setString(6, p.team);
		stmt.setString(7, p.dob);
		stmt.setInt(8, p.id);
		System.out.println(sql);
		stmt.executeUpdate();
	}
	
	public void delete(Player p) throws SQLException {
		String sql = "DELETE FROM players WHERE id=?";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setInt(1, p.id);
		stmt.executeUpdate();
	}

}
