package com.ase.csie;

import com.ase.csie.java.Amenity;
import com.ase.csie.java.Guest;
import com.ase.csie.java.HotelRoom;

//Functional interface can have only one function method
@FunctionalInterface
interface GreetingService {
	void sayMessage(String message);
}

@FunctionalInterface
interface FrontDeskOperation {
	boolean operation(Guest g, HotelRoom hr);
}

//class is used only for example to display usage of a functional interface
class FrontDeskClass {
	public boolean operate(Guest g, HotelRoom hr, FrontDeskOperation mathOperation) {
		return mathOperation.operation(g, hr);
	}
}

public class Main {
	public static void main(String[] args) {
		FrontDeskClass tester = new FrontDeskClass();
		
		//implementation of a functional interface = lambda expression
		FrontDeskOperation checkin = (Guest g1, HotelRoom r1) -> {
			if(r1.isAvailable()) {
				r1.setAvailable(false);
				System.out.println("Guest " + g1.getName() + " has succesfully checked in room " + r1.getRoomNumber());
				return true;
			} else {
				System.out.println("Room " + r1 + " is not available");
				return false;
			}
		};
		
		FrontDeskOperation checkout = (Guest g1, HotelRoom r1) -> {
			r1.setAvailable(true);
			System.out.println("Guest " + g1.getName() + " has succesfully checked out from room " + r1.getRoomNumber());
			return true;
		};
		
		FrontDeskOperation bill = (Guest g1, HotelRoom r1) -> {
			System.out.println("Guest " + g1.getName() + " has to pay " + r1.getPrice() + " for room " + r1.getRoomNumber());
			return true;
		};
		
		FrontDeskOperation checkRoom = (Guest g1, HotelRoom r1) -> r1.isAvailable();
		

		HotelRoom hr = new HotelRoom(1, "East", new Amenity[] {Amenity.MINI_BAR});
//		hr.setAvailable(false);
		Guest g = new Guest("Alice");
		
		HotelRoom hr2 = new HotelRoom(2, "East", new Amenity[] {Amenity.MINI_BAR});
		hr2.setAvailable(false);
		
		System.out.println("Is room " + hr2.getRoomNumber() + " available : " + tester.operate(g, hr2, checkRoom));
		
		if(tester.operate(g, hr, checkin)) {
			tester.operate(g, hr, bill);
			//....
			tester.operate(g, hr, checkout);
		}
		
		
		GreetingService greetingServiceEN = message ->
		System.out.println("Hello " + message);
		
		GreetingService greetingServiceFR = (String msg) ->
		{
			System.out.println("Bonjour " + msg);
		};
		
		greetingServiceEN.sayMessage("Bob");
		greetingServiceFR.sayMessage("Alice");
		
	}
	
	
}
