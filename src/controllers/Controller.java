package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

import models.Model;
import models.Employee;
import views.View;

public class Controller {
	
	private Model model;
	private View view;
	
	public Controller() {
		model = new Model();
	}
	
	public void init() {
		try {
			model.init();
			System.out.println("Connected to database");
			view = new View(this);
		} catch(SQLException e) {
			showMessageDialog(null, "Unable to connect to database");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public ArrayList<Employee> index() {
		try {
			return model.all();
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error fetching Employees");
			return null;
		}
	}
	
	public boolean create(Employee p) {
		try {
			model.create(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error creating Employee");
			return false;
		}
	}
	
	public  boolean update(Employee p) {
		try {
			model.update(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error updating Employee");
			return false;
		}
	}
	
	public boolean delete(Employee p) {
		try {
			model.delete(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error deleting Employee");
			return false;
		}
	}

}
