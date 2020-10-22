package views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controllers.Controller;
import models.Player;

public class View extends JFrame {


	private static final long serialVersionUID = 1L;
	
	protected JPanel panel;
	protected PlayerIndex list;
	protected PlayerForm subPanel;

	public View(Controller controller) {	
		
		panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        
		list = new PlayerIndex(this, controller);
		list.setBorder(new CompoundBorder(
				new TitledBorder("Player list"),
				new EmptyBorder(4, 4, 4, 4)
		));
		
		panel.add(list, gbc);
		
		subPanel = new PlayerForm(this, controller);
		subPanel.setBorder(new CompoundBorder(
				new TitledBorder("Player details"),
				new EmptyBorder(4, 4, 4, 4)
		));
		
		gbc.gridx = 0;
        gbc.gridy = 1;
		panel.add(subPanel, gbc);

		setTitle("Football Player CRM");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public void clearSelection() {
		list.clearSelection();
	}
	
	public void updateList(ArrayList<Player> players) {
		list.update(players);
	}
	
	public void setSelection(Player selection) {
		subPanel.setSelection(selection);
	}


}
