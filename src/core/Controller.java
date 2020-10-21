package core;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	
	private Model model;
	private View view;
	
	public Controller() {
		model = new Model();
		view = new View(this.index(), this);
	}
	
	public ArrayList<Player> index() {
		try {
			return model.all();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean create(Player p) {
		try {
			model.create(p);
			view.updateList(this.index());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public  boolean update(Player p) {
		try {
			model.update(p);
			view.updateList(this.index());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Player p) {
		try {
			model.delete(p);
			view.updateList(this.index());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
