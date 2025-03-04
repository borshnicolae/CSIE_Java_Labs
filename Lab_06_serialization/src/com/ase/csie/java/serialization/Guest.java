package com.ase.csie.java.serialization;

import java.io.Serializable;

public class Guest implements Serializable{
	private static final long serialVersionUID = 164785519450875330L;
	private String name;

	public Guest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
