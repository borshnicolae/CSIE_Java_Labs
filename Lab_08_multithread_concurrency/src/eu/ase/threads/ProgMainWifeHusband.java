package eu.ase.threads;

import java.util.HashMap;
import java.util.Map;

public class ProgMainWifeHusband {
	

	public static void main(String[] args) {
		BankAcc b1 = new BankAcc(1, 200);
		
		Map<Integer, BankAcc> accs = new HashMap<>();
		accs.put(b1.getId(), b1);
		
		BankTransaction transaction = new BankTransaction(200, accs.get(1));
		
		Thread wife = new Thread(transaction, "Wife");
		Thread husband = new Thread(transaction, "Husband");
		Thread t = new Thread(() -> {
			System.out.println("Hell from Thread " + Thread.currentThread().getName());
		});
		
		wife.start();
		husband.start();
		t.start();
		
//		try {
//			wife.join();
//			husband.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("Final balance: " + accs.get(1).getBalance());
		
		System.out.println("MAIN FINISHED");
	}

}
