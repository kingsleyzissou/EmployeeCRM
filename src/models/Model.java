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

	public ArrayList<Employee> all() throws SQLException {
		ArrayList<Employee> list = new ArrayList<>();
		Statement stmt = this.connection.createStatement();
		stmt.executeQuery("SELECT * FROM employees");
		ResultSet res = stmt.getResultSet();
		while(res.next()) {
			Employee employee = new Employee(
				res.getInt("id"),
				res.getInt("employee_number"),
				res.getString("firstname"),
				res.getString("lastname"),
				res.getString("position"),
				res.getString("department"),
				res.getInt("salary")
			);
			list.add(employee);
		}
		return list;
	}
	
	public Employee show(String id) throws SQLException {
		Statement stmt = this.connection.createStatement();
		stmt.executeQuery("SELECT * FROM employees WHERE id = " + id);
		ResultSet res = stmt.getResultSet();
		if (res.next()) {
			return new Employee(
				res.getInt("id"),
				res.getInt("employee_number"),
				res.getString("firstname"),
				res.getString("lastname"),
				res.getString("position"),
				res.getString("department"),
				res.getInt("salary")
			);
		}
		System.out.println("No results found");
		return null;
	}
	
	public void create(Employee e) throws SQLException {
		String sql = "INSERT INTO employees ("
				+ "firstname, lastname, salary,"
				+ "department, position, employee_number"
				+ ") VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setString(1, e.firstname);
		stmt.setString(2, e.lastname);
		stmt.setInt(3, e.salary);
		stmt.setString(4, e.department);
		stmt.setString(5, e.position);
		stmt.setInt(6, e.employee_number);
		stmt.executeUpdate();
	}
	
	public void update(Employee e) throws SQLException {
		String sql = "UPDATE employees SET "
				+ "firstname=?, "
				+ "lastname=?, "
				+ "department=?, "
				+ "position=?, "
				+ "salary=?, "
				+ "employee_number=? "
				+ "WHERE id=?";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setString(1, e.firstname);
		stmt.setString(2, e.lastname);
		stmt.setString(3, e.department);
		stmt.setString(4, e.position);
		stmt.setInt(5, e.salary);
		stmt.setInt(6, e.employee_number);
		stmt.setInt(7, e.id);
		stmt.executeUpdate();
	}
	
	public void delete(Employee p) throws SQLException {
		String sql = "DELETE FROM employees WHERE id=?";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setInt(1, p.id);
		stmt.executeUpdate();
	}

}
