package core;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerForm extends JPanel {
	
	private Model model;
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
	
	public PlayerForm(JPanel panel, Model model) {
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
		
		panel.add(subPanel);
		
		JPanel actionRow = new JPanel();
		actionRow.setLayout(new GridLayout(1, 3));

		actionRow.add(create);
		create.setText("Add");

		actionRow.add(update);
		update.setText("Update");
		
		actionRow.add(delete);
		delete.setText("Delete");
		
//		panel.add(actionRow);
		
		create.addActionListener(e -> this.createListener());
		update.addActionListener(e -> this.updateListener());
		delete.addActionListener(e -> this.deleteListener());
	}
	
	public void setSelection(Player selection) {
		this.selection = selection;
		this.setFormFields();
	}
	
	public void setFormFields() {
		name.setText(this.selection.name);
		lastname.setText(selection.lastname);
		jersey.setText("" + selection.jersey);
		country.setText(selection.country);
		position.setText(selection.position);
		team.setText(selection.team);
		dob.setText(selection.dob);
	}
	
	public void clearForm() {
		this.selection = null;
		name.setText("");
		lastname.setText("");
		jersey.setText("");
		country.setText("");
		position.setText("");
		team.setText("");
		dob.setText("");
	}
	
	public void createListener() {
		if (selection != null) {
			System.out.println("Can't do that");
			return;
		}
		System.out.println("I got clicked");
		try {
			System.out.println(name.getText());
			System.out.println(lastname.getText());
			System.out.println(country.getText());
			System.out.println(position.getText());
			System.out.println(Integer.parseInt(jersey.getText()));
			System.out.println(dob.getText());
			System.out.println(team.getText());
//			model.create(name.getText(), lastname.getText(), Integer.parseInt(jersey.getText()),
//					country.getText(), position.getText(), team.getText(), dob.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateListener() {
		if (selection == null) {
			System.out.println("Can't do that");
			return;
		}
		System.out.println("I got clicked");
		try {
			Player player = new Player(selection.id, name.getText(), lastname.getText(),
					Integer.parseInt(jersey.getText()), country.getText(), position.getText(), team.getText(),
					dob.getText());
			model.update(player);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteListener() {
		if (selection == null) {
			System.out.println("Can't do that");
			return;
		}
		System.out.println("I got clicked");
		try {
			model.delete(selection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
