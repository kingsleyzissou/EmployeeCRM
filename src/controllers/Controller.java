package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Model;
import models.Player;
import views.View;

public class Controller {
	
	private Model model;
	
	public Controller() {
		model = new Model();
		new View(this);
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
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public  boolean update(Player p) {
		try {
			model.update(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Player p) {
		try {
			model.delete(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
