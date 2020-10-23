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
		this.createFormFields(panel);
		this.createButtons(panel);
	}
	
	public void createFormFields(JPanel panel) {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        
        panel.setLayout(new GridBagLayout());
        
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
		
		addComponent(new JLabel("Employee salary:"), 5, 0);
		addComponent(salary, 5, 1);
		
		add(panel);
	}
	
	public void createButtons(JPanel panel) {
		create.addActionListener(e -> this.createListener());
		update.addActionListener(e -> this.updateListener());
		delete.addActionListener(e -> this.deleteListener());
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
		addComponent(create, 7, 0);
		addComponent(update, 7, 1);
		addComponent(delete, 7, 2);
	}
	
	public void addComponent(JComponent component, int y, int x) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.panel.add(component, this.gbc);
	}
	
	public void setSelection(Employee selection) {
		this.selection = selection;
		if (selection != null) this.setFormFields();
	}
	
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
	
	public void createListener() {
		if (this.validateForm(selection == null)) {
			Employee e = this.employeeFromFields();
			controller.create(e);
			this.refresh();
		}
	}
	
	public void updateListener() {
		if (this.validateForm(selection != null)) {
			Employee e = this.employeeFromFields();
			controller.update(e);
			this.refresh();
		}
	}
	
	private void deleteListener() {
		if (selection == null) {
			view.showError("Please select a player before trying to delete");
			return;
		}
		controller.delete(selection);
		this.refresh();
	}
	
	public void refresh() {
		this.clearForm();
		ArrayList<Employee> players = controller.index();
		this.view.updateList(players);
	}
	
	public Employee employeeFromFields() {
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
	
	public boolean isNumeric(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
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
