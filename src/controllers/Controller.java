package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

import models.Model;
import models.Employee;
import views.View;

public class Controller {
	
	/** Model for querying database */
	private Model model;
	
	/** Gui view */
	private View view;
	
	public Controller() {
		model = new Model();
	}
	
	/**
	 * Method to instantiate the controller,
	 * create the model and render the view
	 */
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
	
	/**
	 * Return array list of employees
	 * 
	 * @return list of employees
	 */
	public ArrayList<Employee> index() {
		try {
			return model.all();
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error fetching Employees");
			return null;
		}
	}
	
	/**
	 * Create an employee
	 * 
	 * @param employee to be added
	 * @return truthy value for successful add
	 */
	public boolean create(Employee employee) {
		try {
			model.create(employee);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error creating Employee");
			return false;
		}
	}
	
	/**
	 * Update an employee
	 * 
	 * @param employee to be updated
	 * @return truthy value for successful update
	 */
	public  boolean update(Employee employee) {
		try {
			model.update(employee);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error updating Employee");
			return false;
		}
	}
	
	/**
	 * Delete an employee
	 * 
	 * @param employee to be deleted
	 * @return truthy value for successful delete
	 */
	public boolean delete(Employee employee) {
		try {
			model.delete(employee);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			view.showError("Error deleting Employee");
			return false;
		}
	}

}
