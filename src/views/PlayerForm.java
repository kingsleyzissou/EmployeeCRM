package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Controller;
import models.Player;

public class PlayerForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private View view;
	private Player selection;
	
	private JTextField name = new JTextField();
	private JTextField lastname = new JTextField();
	private JTextField jersey = new JTextField();
	private JTextField team = new JTextField();
	private JTextField country = new JTextField();
	private JTextField dob = new JTextField();
	private JTextField position = new JTextField();
	private JButton create = new JButton();
	private JButton update = new JButton();
	private JButton delete = new JButton();
	
	public PlayerForm(View view, Controller controller) {
		this.controller = controller;
		this.view = view;
		
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(10, 1));
		subPanel.setBackground(Color.WHITE);

		subPanel.add(new JLabel("Player name:"));
		subPanel.add(name);

		subPanel.add(new JLabel("Player lastname"));
		subPanel.add(lastname);

		subPanel.add(new JLabel("Player team"));
		subPanel.add(team);

		subPanel.add(new JLabel("Player position"));
		subPanel.add(position);

		subPanel.add(new JLabel("Player country"));
		subPanel.add(country);

		subPanel.add(new JLabel("Player dob"));
		subPanel.add(dob);

		subPanel.add(new JLabel("Player number"));
		subPanel.add(jersey);
		
		view.addSection(subPanel);
		
		JPanel actionRow = new JPanel();
		actionRow.setLayout(new GridLayout(1, 3));

		actionRow.add(create);
		create.setText("Add");

		actionRow.add(update);
		update.setText("Update");
		
		actionRow.add(delete);
		delete.setText("Delete");
		
		view.addSection(actionRow);
		
		create.addActionListener(e -> this.createListener());
		update.addActionListener(e -> this.updateListener());
		delete.addActionListener(e -> this.deleteListener());
	}
	
	public void setSelection(Player selection) {
		this.selection = selection;
		if (selection != null) this.setFormFields();
	}
	
	public void setFormFields() {
		name.setText(selection.name);
		lastname.setText(selection.lastname);
		jersey.setText("" + selection.jersey);
		country.setText(selection.country);
		position.setText(selection.position);
		team.setText(selection.team);
		dob.setText(selection.dob);
	}
	
	public void clearForm() {
		this.selection = null;
		this.view.clearSelection();
		name.setText(null);
		lastname.setText(null);
		jersey.setText(null);
		country.setText(null);
		position.setText(null);
		team.setText(null);
		dob.setText(null);
	}
	
	public void refresh() {
		this.clearForm();
		ArrayList<Player> players = controller.index();
		this.view.updateList(players);
	}
	
	public boolean validateForm() {
		if (name.getText() == null) return false;
		if (lastname.getText() == null) return false;
		if (jersey.getText() == null) return false;
		if (position.getText() == null) return false;
		if (country.getText() == null) return false;
		if (team.getText() == null) return false;
		if (dob.getText() == null) return false;
		return true;
	}
	
	public Player playerFromFields() {
		int id = selection != null ? selection.id : -1;
		return new Player(
				id,
				name.getText(),
				lastname.getText(),
				Integer.parseInt(jersey.getText()),
				position.getText(),
				country.getText(),
				team.getText(),
				dob.getText()
		);
	}
	
	public void createListener() {
		if (selection != null || !this.validateForm()) {
			System.out.println("Please check your fields and try again");
			return;
		}
		Player p = this.playerFromFields();
		controller.create(p);
		this.refresh();
	}
	
	public void updateListener() {
		if (selection == null || !this.validateForm()) {
			System.out.println("Please check your fields and try again");
			return;
		}
		Player p = this.playerFromFields();
		controller.update(p);
		this.refresh();
	}
	
	private void deleteListener() {
		if (selection == null) {
			System.out.println("Please select a player before trying to delete");
			return;
		}
		controller.delete(selection);
		this.refresh();
	}

}
