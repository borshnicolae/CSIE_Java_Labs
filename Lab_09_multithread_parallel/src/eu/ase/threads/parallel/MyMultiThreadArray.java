package eu.ase.threads.parallel;

public class MyMultiThreadArray implements Runnable {
	private int[] vi;
	
	private int startIdx;
	private int stopIdx;
	private Long sum;
	
	public MyMultiThreadArray(int[] v, 
			int start, int stop) {
		this.vi = v; //if v.clone() used is slower;
		this.startIdx = start;
		this.stopIdx = stop;
	}
	
	@Override
	public void run() {
		long s = 0;
		for (int idx = this.startIdx; idx <= this.stopIdx; 
				idx++) {
			s += this.vi[idx];
		}
		// sum is not incremented directly because it's a object in heap memory and it's
		// slower to call every iteration the object. Either do this way or make sum
		// primitive in order to gain necessary speed performance.
		this.sum = new Long(s);
	}
	
	public Long getSum() {
		return this.sum;
	}
}
