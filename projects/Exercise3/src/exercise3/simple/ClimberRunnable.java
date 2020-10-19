package exercise3.simple;
import java.util.Random;

public class ClimberRunnable implements Runnable {
	private Climber c;

	private Random rand;

	public ClimberRunnable(Climber c) {
		super();
		this.c = c;
		rand = new Random();
	}

	public Climber getC() {
		return c;
	}

	public void setC(Climber c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "ClimberRunnable [c=" + c.getName() + "]";
	}

	@Override
	public void run() {
		try {
			do {
				c.climb();
				System.out.println("Climber " + c.getName() + " up to " + c.getCurrentRung());
				if (!c.done()) {
					Thread.sleep(rand.nextInt(500));
				}
			} while (!c.done());
		} catch(InterruptedException e) {
				System.out.println("Climber " + c.getName() + " interrupted. Exiting.");
				return;
			}
		System.out.println("***** Climber " + c.getName() + " made it.");
	}
}
