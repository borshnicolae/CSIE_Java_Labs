package eu.ase.threads;

class BankTransaction implements Runnable {
	
	private int withdraw;
	private BankAcc bankAcc;
	
	public BankTransaction(int withdraw, BankAcc bankAcc) {
		this.withdraw = withdraw;
		this.bankAcc = bankAcc;
	}
	
	@Override
	public /*synchronized*/ void run() {
		if(withdraw <= bankAcc.getBalance()) {
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			bankAcc.setBalance(bankAcc.getBalance() - withdraw);
			System.out.println(Thread.currentThread().getName() + " withdraw succesfull " + withdraw);
			System.out.println(Thread.currentThread().getName() + " new balance :" + bankAcc.getBalance());
		} else {
			System.out.println("Not suficient funds for " + Thread.currentThread().getName());
		}
		
	}
	
}