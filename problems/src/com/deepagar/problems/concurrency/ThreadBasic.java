package com.deepagar.problems.concurrency;

import java.util.concurrent.*;

/* Thread executes a unit of code usually called Task 
 * Task is defined as class by implementing Runnable interface and void no-args method run()
 * */

class Task implements Runnable{
	public void run() {
		String threadName = Thread.currentThread().getName();
	    System.out.println("Hello " + threadName);
	}
}

class TaskC implements Callable<Integer>{
	public Integer call() {
		try {
			String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		    
		    TimeUnit.SECONDS.sleep(2);
		    
		    return 123;
	    }
	    catch (InterruptedException e) {
	        throw new IllegalStateException("task interrupted", e);
	    }
	}
}

public class ThreadBasic {
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			// create task
			Task task = new Task();
			
			/* directly execute runnable task from main thread
			 * Output -
			 * Hello main
			 */
			task.run();
			
			
			/* execute runnable in separate thread
			 * Output -
			 * Hello Thread-0
			 * Done
			 * 
			 * Each Thread "registers" itself so there is actually a reference to it someplace, 
			 * and the garbage collector can’t clean it up until the task exits its run( ) and dies.
			 */
			
			Thread t = new Thread(task);
			t.start();
			TimeUnit.MILLISECONDS.sleep(10);
			System.out.println("Done");
			
			/* Concurrency API introduces the ExecutorService to abstract thread pool management
			 * and running asynchronous tasks
			 * 
			 * class Executors provides convenient factory methods for creating different kinds of executor services
			 * - Executors have to be stopped explicitly - otherwise they keep listening for new tasks, java process never stops
			 * - shutdown() waits for currently running tasks to finish, prevent new tasks to be submitted  
			 * - shutdownNow() interrupts all running tasks and shut the executor down immediately
			 * 
			 * ExecutorService has execute() and submit() method
			 * - execute: Use it for fire and forget calls. throws un-handled Exception
			 * - submit: Use it to inspect the result of method call and take appropriate action on Future objected returned by the call
			 *           hides un-handled Exception in framework itself
			 * 
			 * newSingleThreadExecutor, newFixedThreadPool, newCachedThreadPool - one thread per task, newScheduledThreadPool    
			 * 
			 * Output -
			 * attempt to shutdown executor
			 * Hello pool-1-thread-1
			 * shutdown finished
			 */
			
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(task);
			
			try {
			    System.out.println("attempt to shutdown executor");
			    executor.shutdown();
			    
			    // bounded shutdown - this could be skipped
			    executor.awaitTermination(5, TimeUnit.SECONDS);
			}
			catch (InterruptedException e) {
			    System.err.println("tasks interrupted");
			}
			finally {
			    if (!executor.isTerminated()) {
			        System.err.println("cancel non-finished tasks");
			    }
			    executor.shutdownNow();
			    System.out.println("shutdown finished");
			}
			
			
			/* Return value from Task
			 * Implement interface Callable (method call()) instead of Runnable (method run())
			 * Callable is a generic with a type parameter representing the return value from call()
			 * Must use ExecutorService submit( ) method with Future object as return value
			 * 
			 * Future
			 * - isDone( ) - to see if it has completed
			 * - get( ) - to get result. this is blocking, or pass timeout option
			 * 
			 * Executors support batch submitting of multiple callables at once via invokeAll() and invokeAny() - first callable result
			 * 
			 * Output - 
			 * future done? false
			 * Hello pool-2-thread-1
			 * java.util.concurrent.TimeoutException
			 * 	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:204)
			 * 	at com.deepagar.problems.concurrency.ThreadBasic.main(ThreadBasic.java:136)
			 * future done? true
			 * result: 123
			 */
			
			// create task with return value
			TaskC taskc = new TaskC();
			
			ExecutorService executor2 = Executors.newFixedThreadPool(1);
			Future<Integer> future = executor2.submit(taskc);
			executor2.shutdown(); // every non-terminated future will throw exceptions if you shutdownNow() the executor

			System.out.println("future done? " + future.isDone());
			
			try {
				// Executing the below code results in a TimeoutException:
				future.get(1, TimeUnit.SECONDS);
			}
			catch(TimeoutException  e) {
				e.printStackTrace();
			}

			Integer result = future.get();

			System.out.println("future done? " + future.isDone());
			System.out.print("result: " + result);
		}
		catch(InterruptedException  e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		catch(ExecutionException  e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

}
