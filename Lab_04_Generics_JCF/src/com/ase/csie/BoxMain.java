package com.ase.csie;

import com.ase.csie.java.box.Box;
import com.ase.csie.java.box.GBox;

public class BoxMain {
	public static void main(String[] args) {
		Box b1 = new Box("2");
		System.out.println(b1.getO());

//		Integer integer = (Integer) b1.getO();// eroare la runtime

		GBox<String> b2 = new GBox<String>("2");
		GBox<Integer> b3 = new GBox<Integer>(2);

		// Integer integer2 = (Integer) b2.getO(); //eroare de compilare
		Integer integer2 = Integer.valueOf(b2.getO());
		System.out.println(integer2);
		Integer integer3 = b3.getO();
	}
}
