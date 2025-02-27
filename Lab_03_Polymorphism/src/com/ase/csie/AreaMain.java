package com.ase.csie;

import eu.ase.java.poly.areaFig.Circle;
import eu.ase.java.poly.areaFig.Square;
import eu.ase.java.poly.areaFig.Tax;

public class AreaMain {

	public static void main(String[] args) {
		Tax areaC = new Circle(12);
		Tax areaS = new Square(12);

//      try(Square areaS1 = new Square(4)){
		try {
			Square areaS1 = new Square(4);
			float calcArea = areaS1.calcArea();
			// areaS1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (areaS instanceof Square) {
			System.out.println("true");
		}
//      
//      System.out.println("Aria cercului: " + areaC.calcArea()/* + ", tax = " + tC.calcTax()*/);
//      System.out.println("Aria patratului: " + areaS.calcArea()/* + ", tax = " + tS.calcTax()*/);
//      

		printTax(areaC);
		printTax(areaS);
		// printTax(areaHexagon); if I add new types of figures, the method remains
		// unchanged
	}

	public static void printTax(Tax t) {
		if (t instanceof Circle) {
			System.out.println("Taxa cercului:");
		} else {
			System.out.println("Taxa patratului:");
		}
		System.out.println(t.calcTax());
	}

}
