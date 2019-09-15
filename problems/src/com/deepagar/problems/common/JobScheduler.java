package com.deepagar.problems.common;



import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


  class MinHeap {
    private int minEpoch;
    private Lock lock;
    private Condition wakeup;
    
    public MinHeap() {
      this.lock = lock;
      this.wakeup = wakeup 
    }
    
    public put(epoch, task) {
      
      lock.lock()
      try {
      // if minEpoch has reduced
      this.wakeup.signal();
      }
      catch() {
      }
      finally {
      }
    }
    
    public final Runnable get() {
      // if epoch of top element of min heap <= current epoch
    }
    
    public int getMinEpoch() {
      
    }
  }
    
  class ScheduleTask implements Runnable{
    
    private ExecutorService executorTask;
    private MinHeap minHeap;
    private Lock lock;
    private Condition wakeup;
    
    public ScheduleTask(ExecutorService executorTask, MinHeap minHeap){
      this.executorTask = executorTask;
      this.minHeap = minHeap;
      this.lock = lock;
      this.wakeup = wakeup;
      
    }
    
    public void run(){
      try{
           this.lock.lock();
          while(true){
            // lookup and get min heap
            final Runnable task = minHeap.get();
            
            if(task == null){
              timeMilliSeconds = minHeap.getMinEpoch();
              this.wakeup.await(timeMilliSeconds, MILLISECONDS);
            }
            else {
              //submit the task to 
              this.executor.submit(task);
            }
          }
      }
      catch() {
      }
      finally {
        lock.unlock();
      }
    }
  }
  
  class JobScheduler {
    private int poolSize;
    private ExecutorService executorTask;
    private MinHeap minHeap;
    private Lock lock;
    private Condition wakeup;
    
    public JobScheduler(int poolSize) {
      this.poolSize = poolSize;
      
      // keep it ready for future change by policy
      this.executorTask = Executors.newFixedThreadPool(this.poolSize);
      this.minHeap = MinHeap(this.lock, this.wakeup);
      
      this.lock = ReentranntLock();
      this.wakeup = this.lock.newCondition();
      
      this.initSchedular();
      
    }
    
    private boolean initSchedular(){
     try {
       // Schedular Task
      while(true){
        
        ScheduleTask task = new ScheduleTask(this.executorTask, this.minHeap, this.lock, this.wakeup);
        Thread thread = new Thread(task);
        thread.start();
      }
     }
      catch() {
      }
    }
    
    
      
    
    public void putJob(final Runnable task, long delayMs){
      
      // calculate execution epoch -> current epoch + delayMs
      
      // queue the task with delay (task, epoch)
      this.minHeap();
      
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
  }
  

