package core;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {


//	private static final long serialVersionUID = 1L;
	
	protected PlayerIndex list;
	protected PlayerForm subPanel;

	public View(ArrayList<Player> players, Model model) {		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));

		list = new PlayerIndex(panel, players);
		subPanel = new PlayerForm(panel, model);

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

		JScrollPane scrollPane = new JScrollPane(panel, v, h);
		scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}


}
