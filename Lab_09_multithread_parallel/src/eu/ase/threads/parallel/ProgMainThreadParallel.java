package eu.ase.threads.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ProgMainThreadParallel {
	
	private static final int NTHREADS = 4; //2
	
	public static void main(String[] args) {
		int dimVect = 80_000_000;
		
		System.out.println("Nr. of cpu's : " + Runtime.getRuntime().availableProcessors());
		
		int [] v = new int[dimVect]; //~300 mb
		Long sum = new Long(0);
		
		for (int i = 0; i < dimVect; i++)
			v[i] = 1 + i;
		
		long startTime = 0, stopTime = 0;
		
		// -----------
		// 1. Sequential
		sum = Long.valueOf(0);
		startTime = System.currentTimeMillis();
		for (int i = 0; i < dimVect; i++) {
			sum += v[i];
		}
		stopTime =  System.currentTimeMillis();
		System.out.println("1. Seq time = " + (stopTime - startTime) 
				+ " , sum = " + sum);
		
		// 2. Multi-threading standard
		sum = new Long(0);
		int startIdx = 0, stopIdx = 0;
		
		Thread[] vectThreads = new Thread[NTHREADS];
		MyMultiThreadArray[] vectRThreads = new MyMultiThreadArray[NTHREADS];

		startTime = System.currentTimeMillis();
		
		//Carefully on NTHREADS, dimVect should be divided exactly by NTHREADS
		for (int it = 0; it < NTHREADS; it++) {
			startIdx = it * (dimVect/NTHREADS);
			stopIdx = (it + 1) * (dimVect/NTHREADS) - 1;
			vectRThreads[it] = new MyMultiThreadArray(v, 
					startIdx, stopIdx);
			vectThreads[it] = new Thread(vectRThreads[it]);
		}
		
		for (int it = 0; it < NTHREADS; it++) {
			vectThreads[it].start();
		}
		
		for (int it = 0; it < NTHREADS; it++) {
			try {
				vectThreads[it].join();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		
		for (int it = 0; it < NTHREADS; it++) {
			sum += vectRThreads[it].getSum(); //vectSum[it]; //vectRThreads[it].getSum();
		}
		
		stopTime =  System.currentTimeMillis();
		System.out.println("2. MultiThread Standard Time = " + (stopTime - startTime) 
				+ " , sum = " + sum);
		
		
		// 3. Multi-threading executor-service
		sum = Long.valueOf(0);
		startTime = System.currentTimeMillis();
		ExecutorService execThreadPool = Executors.newFixedThreadPool(NTHREADS);	
		MyMultiThreadArray[] workerTask = new MyMultiThreadArray[NTHREADS];
				
		for (int it = 0; it < NTHREADS; it++) {
			startIdx = it * (dimVect/NTHREADS);
			stopIdx = (it + 1) * (dimVect/NTHREADS) - 1;
			workerTask[it] = new MyMultiThreadArray(v, 
							startIdx, stopIdx);
			execThreadPool.execute(workerTask[it]);
		}
		
		execThreadPool.shutdown();
		try {
			execThreadPool.awaitTermination(Long.MAX_VALUE, 
					TimeUnit.NANOSECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		for (int it = 0; it < NTHREADS; it++) {
			sum += workerTask[it].getSum(); //vectSum[it]; //vectRThreads[it].getSum();
		}
				
		stopTime =  System.currentTimeMillis();
		System.out.println("3. MultiThread Executor-Service time = " + (stopTime - startTime) 
						+ " , sum = " + sum);
		
		// 4. Future - Callable mechanism
		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
				
		sum = new Long(0);
		startTime = System.currentTimeMillis();
		for(int it = 0; it < NTHREADS; it++) {
			startIdx = it * (dimVect / NTHREADS);
			stopIdx = (it + 1) * (dimVect / NTHREADS) - 1;
					
			//vectSum[slot] = new Long(0);
			Callable<Long> worker = new MyCallableArray(v, startIdx, stopIdx);
			Future<Long> submit = executor.submit(worker);
			list.add(submit);
		} //end for
		
		executor.shutdown();
						
		for (Future<Long> future : list) {
			try {
//				sum += future.get();
				sum += future.get(2, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException ee) {
				ee.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		} // end for - future

//		executor.shutdown();
//		try {
//			executor.awaitTermination(Long.MAX_VALUE,
//							TimeUnit.NANOSECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		stopTime = System.currentTimeMillis();
		System.out.println("4. Array Multithreading - thread pool (ExecutorService) + Callable & Future - stopTime - startTime = "
								+ (stopTime - startTime) + " , sum = " + sum);
		// 5.1 - Fork-Join
		sum = Long.valueOf(0);
		startTime = System.currentTimeMillis();
		sum = SumForkJoin.sumArrays(v);
		stopTime = System.currentTimeMillis();
		System.out.println("5. Fork-Join Parallel Array time = "
						+ (stopTime - startTime) + " , sum = " + sum);	
		
		
		// 6. JAVA 19+ Virtual Threads aka Fabrics
		// set --enable-preview in command line or in VM Arguments of the Run Configuration of Eclipse/IntelliJ
		// please also see: https://github.com/critoma/dad/blob/master/lectures/c01/src/S08_HPC_Threads/eu/ase/threads/ProgMainMultiThreadParallelJava19Fibers.java
		sum = Long.valueOf(0);
		startTime = System.currentTimeMillis();
		
		// Thread[] vectVirtThreads = new Thread[NTHREADS];
		MyMultiThreadArray[] vectVirtRThreads = new MyMultiThreadArray[NTHREADS];
		
//		//@SuppressWarnings("preview")
//		//ThreadFactory factory = Thread.ofVirtual().factory();
//		
		try (@SuppressWarnings("preview")
			var executorServ = Executors.newVirtualThreadPerTaskExecutor()
			// var executorServ = Executors.newThreadPerTaskExecutor(factory)
		) {
			
			for (int it = 0; it < NTHREADS; it++) {
				startIdx = it * (dimVect/NTHREADS);
				stopIdx = (it + 1) * (dimVect/NTHREADS) - 1;
				vectVirtRThreads[it] = new MyMultiThreadArray(v, startIdx, stopIdx);
				executorServ.execute(vectVirtRThreads[it]);
			}
		}
		
//		for (int it = 0; it < NTHREADS; it++) {
//			startIdx = it * (dimVect/NTHREADS);
//			stopIdx = (it + 1) * (dimVect/NTHREADS) - 1;
//			vectSum[it] = Long.valueOf(0);
//			vectVirtRThreads[it] = new MyMultiThreadArray(v, 
//					startIdx, stopIdx);
//			vectVirtThreads[it] = Thread.ofVirtual().unstarted(vectVirtRThreads[it]);
//		}
//		
//		for (int it = 0; it < NTHREADS; it++) {
//			vectVirtThreads[it].start();
//		}
//		
//		for (int it = 0; it < NTHREADS; it++) {
//			try {
//				vectVirtThreads[it].join();
//			} catch(InterruptedException ie) {
//				ie.printStackTrace();
//			}
//		}
		
//		for (int it = 0; it < NTHREADS; it++) {
//			startIdx = it * (dimVect/NTHREADS);
//			stopIdx = (it + 1) * (dimVect/NTHREADS) - 1;
//			vectSum[it] = Long.valueOf(0);
//			vectVirtRThreads[it] = new MyMultiThreadArray(v, startIdx, stopIdx);
//			vectVirtThreads[it] = Thread.startVirtualThread(vectVirtRThreads[it]);
//		}
//		
//		for(int it = 0; it < NTHREADS; it++) {
//			try {
//				vectVirtThreads[it].join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
		for (int it = 0; it < NTHREADS; it++) {
			sum += vectVirtRThreads[it].getSum(); //vectSum[it]; //vectRThreads[it].getSum();
		}
		stopTime = System.currentTimeMillis();
		System.out.println("6. Virtual Threads aka Fabrics time = "
						+ (stopTime - startTime) + " , sum = " + sum);	
	} // end main
} // end class

