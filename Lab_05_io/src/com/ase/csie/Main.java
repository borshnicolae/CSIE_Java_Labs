package com.ase.csie;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.ase.csie.java.io.Guest;
import com.ase.csie.java.io.Transaction;

public class Main {

	// create Transaction class which contains:
	// A. Fields:
	// A.1 - id: int
	// A.2 - guest: Guest
	// A.3 - date: LocalDateTime
	// A.4 - items: String[] - products within transaction
	// A.5 - prices: float[] - product prices
	// B. Methods:
	// B.1 - constructor: public Transaction(int id, Guest guest, LocalDateTime
	// date, String[] items, float[] prices)
	// B.2 - get and set methods
	// B.3 - public void saveTransaction2File(String invoiceFileName) - save the
	// transaction values (in order of the described fields)
	// into a file
	// B.4 - public double readTransactionFromFileAndCalcTotal(String
	// invoiceFileName) - read from the file and calculate
	// the total of the transaction
	// B.5 - clone method for deep copy

	public static void main(String[] args) {
		String[] items = new String[] { "Single Room", "Suite", "Suite" };
		float[] prices = new float[] { 125.5f, 199, 234 };

		Transaction transaction = new Transaction(123, new Guest("Bob"), LocalDateTime.now(), items, prices);

		// fisier binar, HxD viewer
		transaction.saveTransaction2File("test2.txt");
		double totalInvoice = transaction.readTransactionFromFileAndCalcTotal("test2.txt");

		System.out.println("Finish I/O! Total invoice = " + totalInvoice + "$");

		// Homework
		transaction.save2FileString("test.txt");
		totalInvoice = transaction.readAndCalcTotalFromTxt("test.txt");
		System.out.println("\nFinish I/O! Total invoice = " + totalInvoice);

		// Optional
		// Reading information from the Console example
		Scanner scanner = new Scanner(System.in);

		String yourName = "";
		System.out.print("Name: ");
		yourName = scanner.nextLine();
		// Mare atentie ca este blocant!

		System.out.print("Age: ");
		int age = scanner.nextInt();

		System.out.println("Name = " + yourName);
		System.out.println("Age = " + age);

		scanner.close();
	}

}
