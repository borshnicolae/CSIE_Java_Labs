	package eu.ase.java.poly.areaFig;

public class Square implements Tax, AutoCloseable{
	
	private int length;
	
	
	public Square(int length) {
		this.length = length;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public float calcArea() {
		return length*length;
	}


	@Override
	public float calcTax() {
		return calcArea() * TAX;
	}


	@Override
	public void close() throws Exception {
		System.out.println("Do something");
	}
	
	

}
