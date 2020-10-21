package core;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

public class PlayerIndex extends JPanel {
	
	JList<Player> list;
	JPanel panel;
	
	public PlayerIndex(JPanel panel, ArrayList<Player> players) {
		list = new JList<Player>((Player[]) players.toArray());
		list.addListSelectionListener(e -> this.showListener());
		panel.add(list);
	}
	
	public void showListener() {
		Player s = list.getSelectedValue();
//		panel.setSelection(s);
//		((PlayerForm) subPanel).setSelection(s);
	}

}
