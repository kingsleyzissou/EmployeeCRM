package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Controller;
import models.Employee;

public class EmployeeForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private View view;
	private Employee selection;
	
	private JPanel panel =  new JPanel();
	private JTextField name = new JTextField(10);
	private JTextField lastname = new JTextField(10);
	private JTextField salary = new JTextField(10);
	private JTextField department = new JTextField(10);
	private JTextField employee_number = new JTextField(10);
	private JTextField position = new JTextField(10);
	private JButton create = new JButton("Add");
	private JButton update = new JButton("Edit");
	private JButton delete = new JButton("Delete");
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public EmployeeForm(View view, Controller controller) {
		this.controller = controller;
		this.view = view;
		
		// set panel layout
		panel.setLayout(new GridBagLayout());
		add(panel);
		
		// set global grid bag constraints
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		
		// create form elements
		this.createFormFields();
		this.createButtons();
	}
	
	/**
	 * Create the form fields for the panel
	 * 
	 */
	public void createFormFields() {     
		addComponent(new JLabel("Employee number:"), 0, 0);
		addComponent(employee_number, 0, 1);
		
		addComponent(new JLabel("Employee name:"), 1, 0);
		addComponent(name, 1, 1);
		
		addComponent(new JLabel("Employee surname:"), 2, 0);
		addComponent(lastname, 2, 1);
		
		addComponent(new JLabel("Employee position:"), 3, 0);
		addComponent(position, 3, 1);
		
		addComponent(new JLabel("Employee department:"), 4, 0);
		addComponent(department, 4, 1);
		
		addComponent(new JLabel("Employee salary ($):"), 5, 0);
		addComponent(salary, 5, 1);
	}
	
	/**
	 * Create form action buttons
	 * 
	 */
	public void createButtons() {
		// add action listeners for buttons
		create.addActionListener(e -> this.createListener());
		update.addActionListener(e -> this.updateListener());
		delete.addActionListener(e -> this.deleteListener());
		// prevent accidental button clicks
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
		addComponent(create, 7, 0);
		addComponent(update, 7, 1);
		addComponent(delete, 7, 2);
	}
	
	/**
	 * Custom helper function to add component
	 * to panel with GridBagConstraints
	 * 
	 * @param component to be added
	 * @param x grid value
	 * @param y grid value
	 */
	public void addComponent(JComponent component, int y, int x) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.panel.add(component, this.gbc);
	}
	
	/**
	 * Set the selected employee that will be
	 * used by update and delete methods
	 *
	 * @param selection selected employee
	 */
	public void setSelection(Employee selection) {
		this.selection = selection;
		if (selection != null) this.setFormFields();
	}
	
	/**
	 * If employee is selected update
	 * the form fields with the selected
	 * values
	 * 
	 */
	public void setFormFields() {
		employee_number.setText("" + selection.employee_number);
		name.setText(selection.firstname);
		lastname.setText(selection.lastname);
		salary.setText("" + selection.salary);
		position.setText(selection.position);
		department.setText(selection.department);
		create.setEnabled(false);
		update.setEnabled(true);
		delete.setEnabled(true);
	}
	
	/**
	 * Reset the form
	 * 
	 */
	public void clearForm() {
		this.selection = null;
		this.view.clearSelection();
		name.setText(null);
		lastname.setText(null);
		employee_number.setText(null);
		salary.setText(null);
		position.setText(null);
		department.setText(null);
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
	}
	
	/**
	 * Action listener to create a new employee
	 * 
	 */
	public void createListener() {
		// validate form fields
		if (this.validateForm(selection == null)) {
			// convert form fields to employee object
			Employee e = this.employeeFromFields();
			// create employee
			controller.create(e);
			// clear form
			this.refresh();
		}
	}
	
	/**
	 * Action listener to update existing employee
	 * 
	 */
	public void updateListener() {
		// validate form fields
		if (this.validateForm(selection != null)) {
			// convert form fields to employee object
			Employee e = this.employeeFromFields();
			// update employee
			controller.update(e);
			// clear form & selection
			this.refresh();
		}
	}
	
	/**
	 * Action listener to delete existing employee
	 * 
	 */
	private void deleteListener() {
		// validate form
		if (selection == null) {
			view.showError("Please select a player before trying to delete");
			return;
		}
		// delete employee
		controller.delete(selection);
		// clear form && selection
		this.refresh();
	}
	
	/**
	 * Clear the form fields and remove
	 * the selected employee
	 */
	public void refresh() {
		this.clearForm();
		this.selection = null;
		// reload the employees to show up-to-date data
		ArrayList<Employee> players = controller.index();
		// update the list UI
		this.view.updateList(players);
	}
	
	/**
	 * Custom helper method to convert form
	 * fields to Employee object
	 * 
	 * @return employee
	 */
	public Employee employeeFromFields() {
		// use -1 for new employees until database id is returned
		int id = selection != null ? selection.id : -1;
		return new Employee(
				id,
				Integer.parseInt(employee_number.getText()),
				name.getText(),
				lastname.getText(),
				position.getText(),
				department.getText(),
				Integer.parseInt(salary.getText())
		);
	}
	
	/**
	 * Helper function to check if a string is
	 * numeric. This function is used in the form
	 * validation
	 * 
	 * @param value string to be checked
	 * @return truthy value for numeric string
	 */
	public boolean isNumeric(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Front end validation helper to mitigate
	 * incorrect data being processed by model
	 * 
	 * @param startingCondition for create or update methods
	 * @return false for invalid form
	 */
	public boolean validateForm(Boolean startingCondition) {
		String errors = "";
		if (!startingCondition) {
			errors += "- Form error check selected value\n";
		}
		if (name.getText().equals("")) {
			errors += "- Name cannot be empty\n";
		}
		if (lastname.getText().equals("")) {
			errors += "- Surname cannot be empty\n";
		}
		if (salary.getText().equals("")) {
			errors += "- Salary cannot be empty\n";
		};
		if (!isNumeric(salary.getText())) {
			errors += "- Salary must be a number\n";
		};
		if (position.getText().equals("")) {
			errors += "- Position cannot be empty\n";
		}
		if (employee_number.getText().equals("")) {
			errors += "- Employee number cannot be empty\n";
		}
		if (!isNumeric(employee_number.getText())) {
			errors += "- Employee number must be a number\n";
		}
		if (department.getText().equals("")) {
			errors += "- Department cannot be empty\n";
		}
		if (errors.equals("")) return true;
		view.showError(errors);
		return false;
	}

}
