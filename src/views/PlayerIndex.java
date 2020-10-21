package views;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import controllers.Controller;
import models.Player;

public class PlayerIndex extends JPanel {

	private static final long serialVersionUID = 1L;

	ArrayList<Player> players;
	ArrayList<Player> clone;
	ArrayList<Player> filtered;

	JList<Player> list;
	View view;

	public PlayerIndex(View view, Controller controller) {
		this.view = view;
		list = new JList<Player>();
		this.update(controller.index());
		list.addListSelectionListener(e -> this.showListener());
		view.addSection(list);
	}

	public void update(ArrayList<Player> players) {
		this.players = players;
		DefaultListModel<Player> model = new DefaultListModel<Player>();
		model.addAll(players);
		list.setModel(model);
		list.updateUI();
	}

	public void filter(String query) {
		this.clone = this.players;
		String q = query.toUpperCase();
		Predicate<Player> predicate = p -> p.team.toUpperCase().equals(q) || p.name.toUpperCase().equals(q) || p.lastname.toUpperCase().equals(q)
				|| p.position.toUpperCase().equals(q) || p.country.toUpperCase().equals(q);
		this.filtered = (ArrayList<Player>) this.players.stream().filter(predicate).collect(Collectors.toList());
		this.update(this.filtered);
	}

	public void clearSelection() {
		list.clearSelection();
	}

	public void showListener() {
		Player s = list.getSelectedValue();
		view.setSelection(s);
	}

}
