package eu.ase.csie;

public class Client {
	
	private int id; //private, public, protected
	private String name; // String is a class. Package java.lang is imported by default
	
//	public User() { -> default constructor // called even is not defined with new User();
//		//fields gets default values: int = 0, String = null. int can't be null
//	}
	
	public Client(int id, String name) { //private, protected = default
		this.id = id; //this -> pointer to the current instance
		this.name = name;
	}

	public int getId() {
		return id; //return int
	}

	public void setId(int id) {
		this.id = id; // no return for void
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Client cloneUser() {
		Client temp = new Client(this.id, this.name);
		return temp;
	}
}
