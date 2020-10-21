package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {

	private ArrayList<Player> players;
	private Player selection;
	private boolean selected = false;

	private JTextField name = new JTextField();
	private JTextField lastname = new JTextField();
	private JTextField jersey = new JTextField();
	private JTextField team = new JTextField();
	private JTextField country = new JTextField();
	private JTextField dob = new JTextField();
	private JTextField position = new JTextField();
//	private JTextField searchSsnText = new JTextField();
//	private JTextField searchNameText = new JTextField();
	private JButton create = new JButton();
	private JButton update = new JButton();
	private JButton delete = new JButton();

	public View(ArrayList<Player> players, Model model) {
		this.players = players;
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));

//        panel.add(new JLabel("Players"));

		JList<Player> list = new JList(players.toArray());

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				System.out.println("Index Selected: " + index);
				Player s = (Player) list.getSelectedValue();
				System.out.println("Value Selected: " + s.toString());
				selection = s;
				name.setText(s.name);
				lastname.setText(s.lastname);
				jersey.setText("" + s.jersey);
				country.setText(s.country);
				position.setText(s.position);
				team.setText(s.team);
				dob.setText(s.dob);
			}
		});
		
		panel.add(list);

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
		
		panel.add(actionRow);

		create.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selection != null) {
					System.out.println("Can't do that");
					return;
				}
				System.out.println("I got clicked");
				try {
//					System.out.println(name.getText());
//					System.out.println(lastname.getText());
//					System.out.println(country.getText());
//					System.out.println(position.getText());
//					System.out.println(Integer.parseInt(jersey.getText()));
//					System.out.println(dob.getText());
//					System.out.println(team.getText());
					model.create(name.getText(), lastname.getText(), Integer.parseInt(jersey.getText()),
							country.getText(), position.getText(), team.getText(), dob.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selection == null) {
					System.out.println("Can't do that");
					return;
				}
				System.out.println("I got clicked");
				try {
					model.delete(selection);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

		JScrollPane scrollPane = new JScrollPane(panel, v, h);
		scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		setVisible(true);
	}

//	public DefaultTableModel getTableData() {        
//	    DefaultTableModel model = (DefaultTableModel) this.table.getModel();
//	    for (int i = 0; i < players.size(); i++) {
//	        Object[] data = new Object[5];
//	        data[0] = players.get(i).name;
//	        data[1] = players.get(i).lastname;
//	        data[2] = players.get(i).jersey;
//	        data[3] = players.get(i).country;
//	        data[4] = players.get(i).position;
//	        model.addRow(data);
//	    }
//	    return model;
//	}

}
