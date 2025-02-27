package com.ase.arrays;


import java.util.Arrays;
import java.util.Objects;

public class HotelRoom implements Cloneable{
	public static int noRooms; 
	
	private int roomNumber; 
    private String location;
    private Amenity[] amenities;
    private float price;
    private boolean isAvailable;
    
    
    public HotelRoom(int roomNumber, String location, Amenity[] amenities) {
    	this.roomNumber = roomNumber;
    	this.location = location;
    	this.amenities = amenities;
    	noRooms++;
    }

	public int getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Amenity[] getAmenities() {
		Amenity[] copy = new Amenity[this.amenities.length];
		System.arraycopy(amenities, 0, copy, 0, amenities.length);
		return copy;
		
	}

	public void setAmenities(Amenity[] amenities) {
		this.amenities = new Amenity[amenities.length];
		for(int i = 0; i < amenities.length; i++) { 
			this.amenities[i] = amenities[i];
		}
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public float getPrice() {
		this.price = calcPrice();
		return this.price;
	}

	private float calcPrice() {
		float startPrice = 100;
		
		for(Amenity a : this.amenities) {
			//null check before ondemand
			switch(a) {
			case MINI_BAR:
				startPrice+=30.05;
				break;
			case TV:
				startPrice+=20.5;
				break;
			case WIFI:
				startPrice+=10;
				break;
			default:
				break;
			}
		}
		
		return startPrice;
	}
    //////////////////////////////

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(amenities);
		result = prime * result + Objects.hash(isAvailable, price, roomNumber, location);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelRoom other = (HotelRoom) obj;
		return Arrays.equals(amenities, other.amenities) && isAvailable == other.isAvailable
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price) && roomNumber == other.roomNumber
				&& Objects.equals(location, other.location);
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", location=" + location + ", amenities=" + Arrays.toString(amenities)
				+ ", price=" + price + ", isAvailable=" + isAvailable + "]";
	}

	@Override
	public HotelRoom clone() throws CloneNotSupportedException {
		HotelRoom clone =  (HotelRoom) super.clone();
		
		clone.location = new String(this.location);
		
		clone.amenities = this.amenities.clone();
		clone.price = this.calcPrice();
		HotelRoom.noRooms++;
		
		return clone;
	}
	
    
}
