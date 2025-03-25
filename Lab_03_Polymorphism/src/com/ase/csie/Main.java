package com.ase.csie;

import com.ase.csie.java.Amenity;
import com.ase.csie.java.Guest;
import com.ase.csie.java.HotelActions;
import com.ase.csie.java.HotelRoom;
import com.ase.csie.java.SingleRoom;
import com.ase.csie.java.Suite;


public class Main {
	
	public static void main(String[] args) {
		HotelRoom r = new HotelRoom();
		
		r = new SingleRoom();
		r = new Suite();
		//
		SingleRoom sr = new SingleRoom(1, "East", new Amenity[] {Amenity.TV}, 12);
		Suite s = new Suite(2, "West", new Amenity[] {Amenity.TV, Amenity.WIFI}, 3);
		
		//polimorfism -> atuncti cand un tip de obiect poate avea mai multe forme
		r = sr;
		System.out.println(r.toString());
		
		r = s;
		System.out.println(r.toString());
		
		HotelRoom hr1 = null;
		hr1 = sr; //up-cast implicit SingleRoom -> HotelRoom
		sr = (SingleRoom) hr1;
		
		try {
			s = (Suite) hr1;
		} catch(ClassCastException cce) {
			System.out.println("Cast gresit");
			cce.printStackTrace();
		}
		
		if(hr1 instanceof Suite) {
			System.out.println("HR1 IS A SUIT");
		} else if (hr1 instanceof SingleRoom) {
			System.out.println("HR1 IS A SINGLE ROOM");
		}
		
		//clone
		s.setGuests(new Guest[] {new Guest("G1"), new Guest("G2")});
		
		try {
			Suite s2 = s.clone();
//			System.out.println(s2);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		//
		
		HotelActions ha1 = new SingleRoom();
		HotelActions ha2 = new Suite();
		
		ha1.bookRoom();
		ha2.bookRoom();
		
		ha1 = sr;
		ha2 = s;
		
		ha1.calcPrice();
		ha2.calcPrice();
		
		System.out.println("No of hotel rooms = " + HotelRoom.noRooms);
	}
	



}
