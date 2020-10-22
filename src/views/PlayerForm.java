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
import models.Player;

public class PlayerForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private View view;
	private Player selection;
	
	private JPanel panel =  new JPanel();
	private JTextField name = new JTextField(10);
	private JTextField lastname = new JTextField(10);
	private JTextField jersey = new JTextField(10);
	private JTextField team = new JTextField(10);
	private JTextField country = new JTextField(10);
	private JTextField dob = new JTextField(10);
	private JTextField position = new JTextField(10);
	private JButton create = new JButton("Add");
	private JButton update = new JButton("Edit");
	private JButton delete = new JButton("Delete");
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public PlayerForm(View view, Controller controller) {
		this.controller = controller;
		this.view = view;
		this.createFormFields(panel);
		this.createButtons(panel);
	}
	
	public void createFormFields(JPanel panel) {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        
        panel.setLayout(new GridBagLayout());
        
		addComponent(new JLabel("Player name:"), 0, 0);
		addComponent(name, 0, 1);
		
		addComponent(new JLabel("Player lastname:"), 1, 0);
		addComponent(lastname, 1, 1);
		
		addComponent(new JLabel("Player team:"), 2, 0);
		addComponent(team, 2, 1);
		
		addComponent(new JLabel("Player position:"), 3, 0);
		addComponent(position, 3, 1);
		
		addComponent(new JLabel("Player country:"), 4, 0);
		addComponent(country, 4, 1);
		
		addComponent(new JLabel("Player dob:"), 5, 0);
		addComponent(dob, 5, 1);
		
		addComponent(new JLabel("Player number:"), 6, 0);
		addComponent(jersey, 6, 1);
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
		create.setEnabled(false);
		update.setEnabled(true);
		delete.setEnabled(true);
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
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
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
