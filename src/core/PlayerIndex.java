package core;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

public class PlayerIndex extends JPanel {
	
	JList<Player> list;
	View view;
	
	public PlayerIndex(View view, Controller controller) {
		this.view = view;
		this.update(controller.index());
		list.addListSelectionListener(e -> this.showListener());
		view.addSection(list);
	}
	
	public void update(ArrayList<Player> players) {
		list = new JList(players.toArray());
	}
	
	public void showListener() {
		Player s = (Player) list.getSelectedValue();
		view.setSelection(s);
	}
	
	

}
