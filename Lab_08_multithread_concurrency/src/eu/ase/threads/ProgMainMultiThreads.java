package eu.ase.threads;

class ThreadNonSync extends Thread {
	private static int a = 0;
	private static int b = 0;

	public ThreadNonSync(String tName) {
		super(tName);
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("Thread = " + this.getName() + " - a= " + ThreadNonSync.a + ", b= " + ThreadNonSync.b);

			a++;
			try {
				sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b++;
		}
		System.out.println("My thread with name = " + this.getName() + " finished!");
	}
}

class ThreadSync extends Thread {
	private static int a = 0;
	private static int b = 0;
	

	private static final Object myLock = new Object();

	public ThreadSync(String tName) {
		super(tName);
	}

	@Override
	public void run() {
		synchronized (myLock) {
		for (int i = 0; i < 3; i++) {
//			synchronized (myLock) {
				System.out.println("Thread = " + this.getName() + " - a= " + ThreadSync.a + ", b= " + ThreadSync.b);

				a++;
				try {
					sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				b++;
			}
		}
			System.out.println("My thread with name = " + this.getName() + " finished!");
//		}
	}
}

public class ProgMainMultiThreads {

	public static void main(String[] args) throws Exception {
		ThreadNonSync t1 = new ThreadNonSync("T1");
		ThreadNonSync t2 = new ThreadNonSync("T2");

//		ThreadSync t1 = new ThreadSync("TS1");
//		ThreadSync t2 = new ThreadSync("TS2");

		t1.start();
		t2.start();
		
		System.out.println("Main thread finished");
	}

}
