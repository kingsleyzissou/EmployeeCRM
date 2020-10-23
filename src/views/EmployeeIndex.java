package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Controller;
import models.Employee;

public class EmployeeIndex extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Employee> players;
	private Controller controller;
	private View view;

	private JList<Employee> list;
	private JTextField query = new JTextField(10);
    private JButton search = new JButton("Search");
    private JButton clear = new JButton("x");

	public EmployeeIndex(View view, Controller controller) {
		this.controller = controller;
		this.view = view;
		list = new JList<Employee>();
		this.update(controller.index());
		list.addListSelectionListener(e -> this.showListener());
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        
        panel.add(query, gbc);
        
        gbc.gridx = 1;
        panel.add(search, gbc);
        search.addActionListener(e -> this.filter());
        
        gbc.gridx = 2;
        panel.add(clear, gbc);
        clear.addActionListener(e -> this.clearSearch());
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(list, gbc);
		add(panel);
	}

	public void update(ArrayList<Employee> players) {
		this.players = players;
		DefaultListModel<Employee> model = new DefaultListModel<Employee>();
		model.addAll(players);
		list.setModel(model);
		list.updateUI();
	}

	public void filter() {
		if (query.getText() == null) return;
		String q = query.getText().toUpperCase();
		Predicate<Employee> predicate = p -> p.department.toUpperCase().equals(q) || p.firstname.toUpperCase().equals(q) || p.lastname.toUpperCase().equals(q)
				|| p.position.toUpperCase().equals(q);
		ArrayList<Employee> filtered = (ArrayList<Employee>) this.players.stream().filter(predicate).collect(Collectors.toList());
		this.update(filtered);
	}
	
	public void clearSearch() {
		this.query.setText(null);
		this.update(this.controller.index());
	}

	public void clearSelection() {
		list.clearSelection();
	}

	public void showListener() {
		Employee s = list.getSelectedValue();
		view.setSelection(s);
	}

}
