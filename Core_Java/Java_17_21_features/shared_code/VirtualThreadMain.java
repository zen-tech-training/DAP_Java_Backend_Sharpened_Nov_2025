package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadMain {

	private static void lacsOfVirtualThreads() throws Exception {
		long startTime = System.currentTimeMillis();
		
		List<Thread> virtualThreadList = new ArrayList<>();
		for(int i=0;i<100000;i++) {
			Runnable task = () -> {
				try { Thread.sleep(1000); } catch(Exception e) { e.printStackTrace(); }
			};
			Thread t = Thread.ofVirtual().start(task);
			virtualThreadList.add(t);
		}
		for(Thread virtualThread: virtualThreadList) {
			virtualThread.join();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total time required for virtual threads = " + (endTime-startTime));
	}
	
	private static void lacsOfPlatformThreads() {
		long startTime = System.currentTimeMillis();
		
		List<Thread> platformThreadList = new ArrayList<>();
		for(int i=0;i<100000;i++) {
			Runnable task = () -> {
				try { Thread.sleep(1000); } catch(Exception e) { e.printStackTrace(); }
			};
			Thread t = new Thread(task);
			platformThreadList.add(t);
			t.start();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time required for platform threads = " + (endTime-startTime));
	}
	private static void virtualThreadPool() {
		//ExecutorService executorService = Executors.newFixedThreadPool(10); //Executor framework
		ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
		Runnable task1 = ()->System.out.println("Running task 1");
		Runnable task2 = ()->System.out.println("Running task 2");
		
		executorService.submit(task1);
		executorService.submit(task2);
		
		executorService.close();
	}
	private static void startSimpleVirtualThread() throws Exception {
		Runnable task = ()->System.out.println("Running virtual thread");
		
		//OS threads
		//Thread t = new Thread(task);
		//t.start();
		
		//Virtual threads
		Thread virtualThread = Thread.startVirtualThread(task);
		//OR
		virtualThread = Thread.ofVirtual().start(task);
		virtualThread.join();
	}
	public static void main(String[] args) throws Exception {
		//startSimpleVirtualThread();
		//virtualThreadPool();
		//lacsOfPlatformThreads();
		lacsOfVirtualThreads();
	}

}
