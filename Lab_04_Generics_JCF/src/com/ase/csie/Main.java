package com.ase.csie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.ase.csie.java.Amenity;
import com.ase.csie.java.Transaction;
import com.ase.csie.java.HotelRoom;
import com.ase.csie.java.SingleRoom;
import com.ase.csie.java.Suite;

public class Main {
	
	public static void main(String[] args) {
		
		//TODO Define Transactions class
		
		// Generics in Java accept reference types only
		// We need to use the wrapper class Integer
		// instead of the int primitive
		List<Integer> list = new ArrayList<>();
		list.add(1);
		
		// GO INTO interfaces
		// List<HotelRoom> hotel = new Vector<HotelRoom>(10);
		// List<HotelRoom> hotel = new ArrayList<HotelRoom>();
		List<HotelRoom> hotel = new LinkedList<HotelRoom>();// nu este obilgatoriu sa indicam size-ul
		
		System.out.println("Hotel capacity: " + hotel.size());
		
		for(int i = 0; i < 10; i ++) {
			hotel.add(new SingleRoom(i , "East", new Amenity[] {Amenity.TV}, 12));
		}
		for(int i = 10; i < 20; i ++) {
			hotel.add(new Suite(i , "West", new Amenity[] {Amenity.TV, Amenity.WIFI, Amenity.MINI_BAR}, 3));
		}
		System.out.println("Hotel capacity: " + hotel.size());
		
		//Seminar joi

		int size = hotel.size();//another potential issue
		for(int i = 0; i <  hotel.size(); i++) { //ambiguity example not displaying 2nd room becaus shift
			System.out.println(hotel.get(i)); 

			if (i == 1) {
				hotel.remove(i); // use iterator for it
			}
		}
		System.out.println();
		HotelRoom temp = null;
		for(Iterator<HotelRoom> it = hotel.iterator(); it.hasNext();) {
			temp = it.next();
			System.out.println(temp); 
			if(temp.getRoomNumber() == 3) 
				it.remove();
			//temp.print();
		}
		System.out.println();
		for(HotelRoom p : hotel) { //to verify that it was deleted
			System.out.println(p);
		}
		
		///
		//hashcode() is used for HashTable, HashMap, HashSet and compareTo for Comparable collections (TreeMap, TreeSet)
		Map<Transaction, HotelRoom> map = new Hashtable<Transaction, HotelRoom>();
//		Map<Transaction, HotelRoom> map = new TreeMap<Transaction, HotelRoom>();
		// to show that is sorted because of tree, hashtable is random
		for(int i = 0; i < 7; i++) {
			Transaction t = new Transaction();
			t.setId(i);
			t.setDate(LocalDateTime.now());
			HotelRoom r = new SingleRoom(i , "East", new Amenity[] {Amenity.TV}, 12);
			map.put(t, r);
		}
		
		System.out.println(map);
		
		Set<Transaction> s = map.keySet();
        Iterator<Transaction> itp = s.iterator();
        //while(itp.hasNext()) { // equivalent
        for(;itp.hasNext();) {
        	Transaction t = itp.next();
        	HotelRoom r = map.get(t);
        	System.out.println("Transaction id: " + t.getId() + ", date : " + t.getDate().toString() + ", room: " + r);
        }
		
	}

}
