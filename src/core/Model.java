package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
	
	private Connection connection = null;
	
	public Model(Connection connection) {
		this.connection = connection;
	}

	public ResultSet index() throws SQLException {
		Statement stmt = this.connection.createStatement();
		stmt.executeQuery("SELECT * FROM players");
		return stmt.getResultSet();
	}
	
	public ResultSet show(String id) throws SQLException {
		Statement stmt = this.connection.createStatement();
		stmt.executeQuery("SELECT * FROM players WHERE id = " + id);
		return stmt.getResultSet();
	}

}
