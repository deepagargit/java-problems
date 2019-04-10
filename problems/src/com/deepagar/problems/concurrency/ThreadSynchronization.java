package com.deepagar.problems.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/* Thread executes a unit of code usually called Task 
 * Task is defined as class by implementing Runnable interface and void no-args method run()
 * */

class TaskCount implements Runnable{
	private int count = 0;
	
	private void increment() {
		int tmp = this.count;
		tmp = tmp + 1;
		Thread.yield();
		this.count = tmp;
	}
	
	public int get() {
		return this.count;
	}
	
	public void run() {
	    this.increment();
	}
	
}


class TaskCountSync implements Runnable{
	private int count = 0;
	
	private void incrementSync() {
		/* Uses object level monitor or monitor lock for synchronization
		 * All implicit monitors implement the reentrant characteristics
		 */
		synchronized (this) {
			int tmp = this.count;
			tmp = tmp + 1;
			Thread.yield();
			this.count = tmp;
		}
		
	}
	
	public int get() {
		return this.count;
	}
	
	public void run() {
	    this.incrementSync();
	}
	
}


class TaskCountLock implements Runnable{
	private int count = 0;
	
	/* Concurrency API supports various explicit locks specified by the Lock interface
	 * Locks support various methods for finer grained lock control thus are more expressive than implicit monitors
	 * Different variation of lock, Lock, ReadWriteLock, StampedLock
	 */
	private ReentrantLock lock;
	
	private void incrementLock() {
		this.lock.lock();
		try {
			int tmp = this.count;
			tmp = tmp + 1;
			Thread.yield();
			this.count = tmp;
		}
		finally {
			this.lock.unlock();
		}
		
	}
	
	public TaskCountLock() {
		this.lock = new ReentrantLock();
	}
	
	public int get() {
		return this.count;
	}
	
	public void run() {
	    this.incrementLock();
	}
	
}

public class ThreadSynchronization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			// create task
			TaskCount task = new TaskCount();
			
			/* Execute without synchronization would lead to race condition
			 * 
			 * Output - 
			 * TaskCount : 3070
			 */
			ExecutorService executor = Executors.newFixedThreadPool(100);
			for(int i = 0; i<10000; i++) {
				executor.execute(task);
			}
			
			executor.shutdown();
			executor.awaitTermination(10, TimeUnit.SECONDS);
			System.out.println("TaskCount isTerminated : " + executor.isTerminated());
			System.out.println("TaskCount : " + task.get());
			
			/* Execute with synchronization 
			 * 
			 * Output - 
			 * TaskCountSync : 10000
			 */
			
			// create task
			TaskCountSync taskSync = new TaskCountSync();
						
			ExecutorService executor2 = Executors.newFixedThreadPool(2);
			for(int i = 0; i<10000; i++) {
				executor2.execute(taskSync);
			}
			
			executor2.shutdown();
			executor2.awaitTermination(10, TimeUnit.SECONDS);
			
			System.out.println("TaskCountSync isTerminated : " + executor2.isTerminated());
			System.out.println("TaskCountSync : " + taskSync.get());
			
			/* Execute with lock 
			 * 
			 * Output - 
			 * TaskCountLock : 10000
			 */
			
			// create task
			TaskCountLock taskLock = new TaskCountLock();
						
			ExecutorService executor3 = Executors.newFixedThreadPool(2);
			for(int i = 0; i<10000; i++) {
				executor3.execute(taskLock);
			}
			
			executor3.shutdown();
			executor3.awaitTermination(10, TimeUnit.SECONDS);
			
			System.out.println("TaskCountLock isTerminated : " + executor3.isTerminated());
			System.out.println("TaskCountLock : " + taskLock.get());
			
		}
		catch(InterruptedException  e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		finally {
			System.out.println("finally done");
		}

	}

}
