package TestThread;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		TestThread test1 = new TestThread();


		Thread t1 = new Thread(new Runnable()
		{
			public void run()
			{
				test1.sum();
			}
		});

		Thread t2 = new Thread(new Runnable ()
		{
			public void run()
			{
				test1.mul();
			}

		});
		
		t1.start();
		t2.start();
	}
	
	



}
