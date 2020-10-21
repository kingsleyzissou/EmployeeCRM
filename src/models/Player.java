package models;

public class Player {
	
	public int id;
	public String name;
	public String lastname;
	public int jersey;
	public String position;
	public String country;
	public String team;
	public String dob;
	
	public Player(
			int id,
			String name, 
			String lastname, 
			int jersey, 
			String position, 
			String country,
			String team,
			String dob
	) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.jersey = jersey;
		this.position = position;
		this.country = country;
		this.team = team;
		this.dob = dob;
	}
	
	public String toString() {
		return  this.jersey
				+ " - "
				+ this.name 
				+ " " 
				+ this.lastname 
				+ " (" 
				+ this.position 
				+ ")"
				+ " - "
				+ this.team;
	}

}
