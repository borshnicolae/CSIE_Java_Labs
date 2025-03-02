package com.ase.csie.java;

public class SingleRoom extends HotelRoom {
	public float area;

	public SingleRoom() {
		this.area = 0;
		System.out.println("SingeRoom:Constructor");
	}

	public SingleRoom(int roomNumber, String location, Amenity[] amenities, float area) {
		super(roomNumber, location, amenities);
		this.area = area;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	@Override
	public void bookRoom() {
		if (super.isAvailable()) {
			setAvailable(false);
			System.out.println("Single Room was booked");
		} else {
			System.out.println("Single Room is not available");
		}
	}

	@Override
	public String toString() {
		return new String("SingleRoom nr " + this.getRoomNumber() + " has area of " + this.getArea());
	}

	@Override
	public float calcPrice() {
		this.price = super.calcPrice();
		System.out.println("Single room price: " + this.price);
		return price;
	}

}
