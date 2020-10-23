package views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import static javax.swing.JOptionPane.showMessageDialog;

import controllers.Controller;
import models.Employee;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private EmployeeIndex index;
	private EmployeeForm form;

	private JPanel panel = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public View(Controller controller) {	
		// set panel layout
        panel.setLayout(new GridBagLayout());
       
        // set gridbag options
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        
        // create new form view and set border
 		form = new EmployeeForm(this, controller);
 		form.setBorder(new CompoundBorder(
 				new TitledBorder("Employee details"),
 				new EmptyBorder(4, 4, 4, 4)
 		));
        
        // create new list view and add border
		index = new EmployeeIndex(this, controller);
		index.setBorder(new CompoundBorder(
				new TitledBorder("Employee list"),
				new EmptyBorder(4, 4, 4, 4)
		));
		
		addComponent(form, 0, 0);
		addComponent(index, 1, 0);

		// set JFrame options
		setTitle("Employee CRM");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	/**
	 * Custom helper function to add component
	 * to panel with GridBagConstraints
	 * 
	 * @param component to be added
	 * @param x grid value
	 * @param y grid value
	 */
	public void addComponent(JComponent component, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		panel.add(component, gbc);
	}

	/**
	 * Clear the selected employee
	 * 
	 */
	public void clearSelection() {
		index.clearSelection();
	}
	
	/**
	 * Update the employee list and
	 * refresh the UI
	 * 
	 * @param employees
	 */
	public void updateList(ArrayList<Employee> employees) {
		index.update(employees);
	}
	
	/**
	 * Set the selected employee
	 * for the form field
	 * 
	 * @param selection employee
	 */
	public void setSelection(Employee selection) {
		form.setSelection(selection);
	}

	/**
	 * Display error message
	 * 
	 * @param message
	 */
	public void showError(String message) {
		showMessageDialog(null, message);
	}


}
