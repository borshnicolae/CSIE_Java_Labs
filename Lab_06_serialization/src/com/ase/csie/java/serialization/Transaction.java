package com.ase.csie.java.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Transaction implements Comparable<Transaction>, Cloneable, Serializable {
	private static final long serialVersionUID = -2430074552557770434L;
	private int id;
	private Guest guest;
	private LocalDateTime date;
	private String[] items;
	private float[] prices;

	public Transaction(int id, Guest guest, LocalDateTime date, String[] items, float[] prices) {
		this.id = id;
		this.guest = guest;
		this.date = date;
		this.items = items;
		this.prices = prices;
	}

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
		else if (this.date.isAfter(o.date))
			return 1;
		else
			return -1;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public float[] getPrices() {
		return prices;
	}

	public void setPrices(float[] prices) {
		this.prices = prices;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", guest=" + guest.getName() + ", date=" + date + ", items="
				+ Arrays.toString(items) + ", prices=" + Arrays.toString(prices) + "]";
	}

	public void saveTransaction2File(String fileName) {
		DataOutputStream out = null;
		// scriere binara
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			out = new DataOutputStream(bos);

			out.writeInt(this.id);
			out.writeUTF(this.guest.getName());
			out.writeUTF(this.date.format(DateTimeFormatter.ISO_DATE_TIME));

			for (int i = 0; i < items.length; i++) {
				out.writeUTF(this.items[i]);
				out.writeFloat(this.prices[i]);
			}

//			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public double readTransactionFromFileAndCalcTotal(String fileName) {
		double total = 0.0f;
//		DataInputStream in = null;

		try {
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream in = new DataInputStream(bis);

			int id = 0;
			Guest guest = null;
			LocalDateTime date = null;
			String item;
			float price;

			try {
				id = in.readInt();
				guest = new Guest(in.readUTF());
				date = LocalDateTime.parse(in.readUTF(), DateTimeFormatter.ISO_DATE_TIME);
				System.out.printf("Read record - id = %d, guest = %s, date = %s \nwith transaction items: ", id,
						guest.getName(), date.toString());
				while (true) {
					item = in.readUTF();
					price = in.readFloat();
					// Like in C
					System.out.printf("\n %s	%f $", item, price);
					total += price;
				}
			} catch (EOFException eof) {
				System.out.println("\n Total = " + total + "$");
				in.close();
			}

			in.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return total;
	}

	// Homework
	public void save2FileString(String fileName) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream);
			BufferedWriter writer = new BufferedWriter(outputWriter);
			// write each field on one line
			writer.write(String.valueOf(this.id));
			writer.write(System.lineSeparator());
			writer.write(String.valueOf(this.guest.getName()));
			writer.write(System.lineSeparator());
			writer.write(String.valueOf(this.date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
			writer.write(System.lineSeparator());

			for (int i = 0; i < this.prices.length; i++) {
				writer.write(this.items[i]);
				writer.write(System.lineSeparator());
				writer.write(String.valueOf(this.prices[i]));
				writer.write(System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double readAndCalcTotalFromTxt(String fileName) {
		double price = 0.0;
		String item = "";

		double total = 0.0;

		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);

			int id = Integer.parseInt(reader.readLine());
			Guest guest = new Guest(reader.readLine());
			LocalDateTime date = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			System.out.printf("Read record - id = %d, guest = %s, date = %s \nwith transaction items: ", id,
					guest.getName(), date.toString());
			for (int i = 0; i < prices.length; i++) {
				item = reader.readLine();
				price = Float.parseFloat(reader.readLine());
				
				System.out.printf("\n %s	%f $", item, price);
				total += price;
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return total;
	}

	@Override
	public Transaction clone() throws CloneNotSupportedException {
		Transaction clone = (Transaction) super.clone();

		clone.setGuest(new Guest(this.getGuest().getName()));
		clone.setItems(this.items.clone());
		clone.setPrices(this.prices.clone());
		return clone;
	}

}
