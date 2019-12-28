package TestThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

	Lock l1 = new ReentrantLock();
	public synchronized void sum()
	{
		System.out.println("Sum notifying");

try {
	Thread.sleep(1000);
	l1.notify();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		
	}
	
	public synchronized void mul()
	{
		try {
			
				System.out.println("waiting completed");
				l1.wait();	
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mul running");
	}
}
