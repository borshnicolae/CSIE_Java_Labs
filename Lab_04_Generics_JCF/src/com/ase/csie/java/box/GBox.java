package com.ase.csie.java.box;

public class GBox<T> {
	private T o;

	public GBox(T o) {
		super();
		this.o = o;
	}

	public T getO() {
		return o;
	}

	public void setO(T o) {
		this.o = o;
	}
	
}
