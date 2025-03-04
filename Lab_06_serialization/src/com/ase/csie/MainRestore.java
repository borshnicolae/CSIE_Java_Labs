package com.ase.csie;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.ase.csie.java.serialization.Guest;
import com.ase.csie.java.serialization.Transaction;


public class MainRestore {

	public static void main(String[] args) {
		try {
			System.out.println("Objects Restore ...");
															// Anonymous Object
			ObjectInputStream sin = new ObjectInputStream(new BufferedInputStream(new FileInputStream("test4.txt")));
			
			Guest g1 = (Guest)sin.readObject();
			System.out.println("g1 read = " + g1.getName());
			
			Transaction t1 = (Transaction)sin.readObject();
			System.out.println("t1 read = " + t1);

			Transaction t2 = (Transaction)sin.readObject();
			System.out.println("t2 read = " + t2);
			
			boolean exp = ((t1.getGuest() == g1) && (t1.getGuest() == t2.getGuest()));
			System.out.println("exp boolean = " + exp);
						
			sin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
