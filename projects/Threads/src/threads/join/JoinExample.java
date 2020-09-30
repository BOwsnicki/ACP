package threads.join;

public class JoinExample { 
	public static void main (String[] args) 
	{ 

		// creating some threads 
		JoiningThread t1 = new JoiningThread("Thread 1"); 
		JoiningThread t2 = new JoiningThread("Thread 2"); 
		JoiningThread t3 = new JoiningThread("Thread 3"); 

		// thread t1 starts 
		t1.start(); 

		// starts second thread after when 
		// first thread t1 has died. 
		try
		{ 
			System.out.println("Current Thread: "
				+ Thread.currentThread().getName()); 
			t1.join(); 
		} 

		catch(Exception ex) 
		{ 
			System.out.println("Exception has " + 
								"been caught" + ex); 
		} 

		// t2 starts 
		t2.start(); 

		// starts t3 after when thread t2 has died. 
		try
		{ 
			System.out.println("Current Thread: "
				+ Thread.currentThread().getName()); 
			t2.join(); 
		} 

		catch(Exception ex) 
		{ 
			System.out.println("Exception has been" + 
									" caught" + ex); 
		} 

		t3.start(); 
	} 
} 
