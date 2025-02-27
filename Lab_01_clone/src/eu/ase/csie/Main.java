package eu.ase.csie;

public class Main {

	public static void main(String[] args) {
		//System.out.println("Hello World!");
		
		Client c1 = new Client(1, "Alice");
		System.out.println("Client c1: id = " + c1.getId() + ", name = " + c1.getName());
		//garbage collector
		System.out.println();
		
		Client c2 = c1;
		System.out.println("Client c1: id = " + c1.getId() + ", name = " + c1.getName());
		System.out.println("Client c2: id = " + c2.getId() + ", name = " + c2.getName());
		System.out.println();
		
		c2.setName("Bob");
		System.out.println("Client c1: id = " + c1.getId() + ", name = " + c1.getName());
		System.out.println("Client c2: id = " + c2.getId() + ", name = " + c2.getName());
		System.out.println();
		
		c2 = c1.cloneUser();
		c2.setId(2);
		c2.setName("Alice");
		System.out.println("Client c1: id = " + c1.getId() + ", name = " + c1.getName());
		System.out.println("Client c2: id = " + c2.getId() + ", name = " + c2.getName());
		
		
		//String immutable //not necessary now
		//java is call by value only
		//modUs(c2);
		//System.out.println("Client c2: id = " + c2.getId() + ", name = " + c2.getName());
	}

	public static void modUs(Client u) {
		u.setId(3);
	}
}
