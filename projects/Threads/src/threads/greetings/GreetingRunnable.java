package threads.greetings;

import java.util.Date;

/**
 * A runnable that repeatedly prints a greeting.
 */
public class GreetingRunnable implements Runnable {
	private static final int REPETITIONS = 20;
	private static final int DELAY = 1000;
	
	private String greeting;

	public GreetingRunnable(String greeting) {
		this.greeting = greeting;
	}

	public void run() {
		try {
			for (int i = 1; i <= REPETITIONS; i++) {
				Date now = new Date();
				System.out.println(now + " " + greeting);
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException exception) {
			System.out.println("Thread: " + greeting + " interrupted!");
		}
		System.out.println("Thread: " + greeting + " terminated!");
	}
}
