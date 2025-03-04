package com.ase.csie.java;

public class Suite extends HotelRoom {
	private int roomsNumber;
	private Guest[] guests;

	public Suite() {
		this.roomsNumber = 0;
		System.out.println("Suite:Constructor");
	}

	public Suite(int roomNumber, String location, Amenity[] amenities, int roomsNumber) {
		super(roomNumber, location, amenities);
		this.setRoomsNumber(roomsNumber);
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public Guest[] getGuests() {
		return guests;
	}

	public void setGuests(Guest[] guests) {
		this.guests = guests;
	}

	@Override
	public void bookRoom() {
		if (super.isAvailable()) {
			setAvailable(false);
			System.out.println("Suite was booked");
		} else {
			System.out.println("Suite is not available");
		}
	}

	@Override
	public float calcPrice() {
		float initialPrice = super.calcPrice();
		this.price = initialPrice * roomsNumber;
		System.out.println("Suite price: " + this.price);
		return super.price;
	}

	@Override
	public String toString() {
		return new String("Suite nr " + this.getRoomNumber() + " has " + this.getRoomsNumber() + " rooms");
	}

	@Override
	public Suite clone() throws CloneNotSupportedException {
		Suite clone = (Suite) super.clone();
		Guest[] copy = this.guests.clone();
		for (int i = 0; i < this.guests.length; i++) {
			copy[i] = new Guest(new String(this.guests[i].getName()));
		}
		clone.setGuests(copy);

		return clone;
	}

}
