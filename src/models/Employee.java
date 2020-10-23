package models;

public class Employee {
	
	public int id;
	public int employee_number;
	public String firstname;
	public String lastname;
	public String position;
	public String department;
	public int salary;
	
	public Employee(
			int id,
			int employee_number,
			String firstname, 
			String lastname, 
			String position,
			String department,
			int salary
			
	) {
		this.id = id;
		this.employee_number = employee_number;
		this.firstname = firstname;
		this.lastname = lastname;
		this.position = position;
		this.department = department;
		this.salary = salary;
	}
	
	public String toString() {
		return  this.employee_number
				+ " - "
				+ this.firstname 
				+ " " 
				+ this.lastname 
				+ " (" 
				+ this.position 
				+ ")"
				+ " - "
				+ this.department
				+ " "
				+ this.salary;
	}

}
