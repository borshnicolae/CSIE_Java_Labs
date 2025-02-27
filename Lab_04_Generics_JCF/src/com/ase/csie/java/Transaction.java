package com.ase.csie.java;

import java.time.LocalDateTime;

public class Transaction implements Comparable<Transaction>{
	private int id;
	private LocalDateTime date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public int compareTo(Transaction o) {
		if (this.date.isEqual(o.date))
			return 0;
		else if(this.date.isAfter(o.date))
			return 1;
		else
			return -1;
	}

	
	
	
}
