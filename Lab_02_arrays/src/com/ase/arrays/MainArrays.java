package com.ase.arrays;

public class MainArrays {
	
//	private static int a;
	
	public static void main(String[] args) {

		//commentarii /**/
		
		//In methods primitives has to be initialized before used.
		//Primitives:  Wrappers
		//byte -> 1 byte  Byte
		//short -> 2 bytes Short
		//int -> 4 bytes Integer
		//long -> 8 bytes Long 2l
		//float -> 4 bytes Float 2.0f
		//double -> 8 bytes Double 2.0d
		//boolean -> 1 bit Boolean
		//char -> 2 bytes Character 'a'
		long x = 500000000000000l;//by default is considered int
		float f = 2.2f; //by default is considered double
		Integer a = 1;//autoboxing
		int b = a;
		a=b;
		
		//final keyword

		int studentsNo = 2;
		int lectNo = 3;
		float[] avgStudentMark = new float[studentsNo]; //masivele in java sunt referinte deci se init cu new

//		short[][] studentsMarksAtDisciplines = new short[studentsNo][lectNo];
		short[][] studentsMarksAtDisciplines = new short[][] { { 5, 8, 9 }, { 8, 9, 7 } }; //masiv multidimensional
		
		for(int i = 0; i < studentsNo; i++) {
			avgStudentMark[i] = 0;
			for(int j = 0; j < lectNo; j++) {
				//avgStudentMark[i] = avgStudentMark[i] + studentsMarksAtDisciplines[i][j]
				avgStudentMark[i] += studentsMarksAtDisciplines[i][j];
			}
			avgStudentMark[i] /= lectNo;
		}
		
		for(int i = 0; i < studentsNo; i++) {
			System.out.println("The average mark for student - " + i + " is " + avgStudentMark[i]);
		}
		
		Integer a1 = 1;
		Integer b1 = 1;
		b1 = 2;

	}

}
