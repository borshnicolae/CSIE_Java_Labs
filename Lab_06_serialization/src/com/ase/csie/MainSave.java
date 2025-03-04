package com.ase.csie;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

import com.ase.csie.java.serialization.Guest;
import com.ase.csie.java.serialization.Transaction;


public class MainSave {

	public static void main(String[] args) {
		try {
			System.out.println("Saving objects ...");
			FileOutputStream fos = new FileOutputStream("test4.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream sout = new ObjectOutputStream(bos);

			Guest g1 = new Guest("Bob");
			Transaction t1 = new Transaction(1, g1, LocalDateTime.now(), new String[] {"Single Room"}, new float[] {125});
			Transaction t2 = new Transaction(2, g1, LocalDateTime.now(), new String[] {"Double Room"}, new float[] {159.9f});
			
			sout.writeObject(g1);
			sout.writeObject(t1);
			sout.writeObject(t2);
			//optional
			//sout.flush();

			System.out.println("g1 written: " + g1.toString());//toString optional, it's called implicitly
			System.out.println("t1 written: " + t1.toString());
			System.out.println("t2 written: " + t2.toString());


			boolean exp = ((t1.getGuest() == g1) && (t1.getGuest() == t2.getGuest()));
			System.out.println("exp boolean = " + exp);

			sout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
