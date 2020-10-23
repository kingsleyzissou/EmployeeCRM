package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Controller;
import models.Employee;

public class EmployeeIndex extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Employee> employees;
	private Controller controller;
	private View view;

	private JList<Employee> list = new JList<Employee>();
	private JTextField query = new JTextField(10);
    private JButton search = new JButton("Search");
    private JButton clear = new JButton("x");
    private JPanel panel = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();

	public EmployeeIndex(View view, Controller controller) {
		this.controller = controller;
		this.view = view;
		
		// Set layout options
		panel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        add(panel);
        
        // add components
        addComponent(query, 0, 0, 1);
        addComponent(search,  1, 0, 1);
        addComponent(clear, 2, 0, 1);
        addComponent(list, 0, 1, 3);
        
        update(controller.index());
        // add event listeners
        list.addListSelectionListener(e -> this.setSelection());
        search.addActionListener(e -> this.filter());
        clear.addActionListener(e -> this.clearSearch());
	}
	
	/**
	 * Refresh the JList component
	 * with updated data
	 * 
	 * @param employees
	 */
	public void update(ArrayList<Employee> employees) {
		this.employees = employees;
		DefaultListModel<Employee> model = new DefaultListModel<Employee>();
		model.addAll(employees);
		list.setModel(model);
		list.updateUI();
	}

	/**
	 * Filter the employee list based
	 * on the search criteria
	 * 
	 */
	public void filter() {
		if (query.getText() == null) return;
		// Convert string to upper case for comparison
		String q = query.getText().toUpperCase();
		Predicate<Employee> predicate = e -> getPredicate(e, q);
		ArrayList<Employee> filtered = (ArrayList<Employee>) this.employees
				// stream data
				.stream()
				// filter on query predicate
				.filter(predicate)
				// return list
				.collect(Collectors.toList());
		// display filtered results
		this.update(filtered);
	}
	
	/**
	 * Compare query parameter and employee and
	 * return a boolean value for whether the employee
	 * matches the search criteria or not
	 * 
	 * @param e employee to compare to
	 * @param q query string
	 * @return truth if employee matches query string
	 */
	public boolean getPredicate(Employee e, String q) {
		return e.department.toUpperCase().equals(q) 
				|| e.firstname.toUpperCase().equals(q) 
				|| e.lastname.toUpperCase().equals(q)
				|| e.position.toUpperCase().equals(q) 
				|| ("" + e.salary).equals(q) 
				|| ("" + e.employee_number).equals(q);
	}
	
	/**
	 * Clear the search query
	 * 
	 */
	public void clearSearch() {
		this.query.setText(null);
		this.update(this.controller.index());
	}

	/**
	 * Select an employee
	 * 
	 */
	public void setSelection() {
		Employee s = list.getSelectedValue();
		view.setSelection(s);
	}
	
	/**
	 * Clear employee selection
	 * 
	 */
	public void clearSelection() {
		list.clearSelection();
	}
	
	/**
	 * Custom helper function to add component
	 * to panel with GridBagConstraints
	 * 
	 * @param component to be added
	 * @param x grid value
	 * @param y grid value
	 * @param width grid width
	 */
	public void addComponent(JComponent component, int x, int y, int width) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		panel.add(component, gbc);
	}

}
