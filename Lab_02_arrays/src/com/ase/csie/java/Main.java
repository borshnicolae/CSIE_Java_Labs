package com.ase.csie.java;


public class Main {
	
//	int a;
	public static void main(String[] args) throws CloneNotSupportedException {
//		int a;
//		System.out.println(a);
				
		Amenity[] amenities = new Amenity[] {Amenity.TV, Amenity.WIFI};
		HotelRoom r1 = new HotelRoom(1, "East", amenities);
		//TODO Start from here. (Miercuri)
//		amenities[0] = Amenity.MINI_BAR;
		HotelRoom r2 = new HotelRoom(2, "West", new Amenity[] {Amenity.TV, Amenity.WIFI, Amenity.MINI_BAR});
		
		HotelRoom[] hotel = new HotelRoom[HotelRoom.noRooms];
		hotel[0] = r1;
		hotel[1] = r2;
		
		//simple for or for each
		for (HotelRoom r : hotel) {
			System.out.println("The price for room nr. " + r.getRoomNumber() + " with location: " + r.getLocation() + " is " + r.getPrice());
		}
		
		//////////
		System.out.println(hotel[0].hashCode());
		System.out.println(hotel[1].hashCode());
		
		System.out.println(hotel[0].equals(hotel[1]));
		
		HotelRoom r3 = (HotelRoom) r1.clone();
		
		r1.getAmenities()[0] = Amenity.MINI_BAR;
//		r1.setAmenities(new Amenities[] {Amenities.MINI_BAR, Amenities.WIFI});
		
		System.out.println(r3.toString());
		System.out.println(r3.hashCode());
		System.out.println(r3.equals(r1));
		
	}

}
