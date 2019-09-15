package com.deepagar.problems.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	
	private String[] buffer;
	
	private int rear;
	private int front;
	private int capacity;
	private int count;
	
	private final Lock lock = new ReentrantLock();
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull = lock.newCondition();
	
	public BoundedBuffer(int capacity) {
		rear = 0;
		front = 0;
		this.capacity = capacity;
		count = 0;
	}
	
	public void deposit(String data) throws InterruptedException {
		lock.lock();
		
		try {
			
			while(count == capacity) {
				notFull.await();
			}
			
			buffer[rear] = data;
			rear = (rear + 1)%capacity;
			count++;
			
			notEmpty.signal();
			
		}
		finally {
			lock.unlock();
		}
	}
	
	public String fetch() throws InterruptedException {
		lock.lock();
		
		try {
			while(count == 0) {
				notEmpty.await();
			}
			
			String data = buffer[front];
			front = (front + 1) % capacity;
			count--;
			
			notFull.signal();
			
			return data;
		}
		finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
