package exercise3.tricky;
import java.util.Random;

public class ClimberRunnable implements Runnable {
	private Climber c;
	private Thread owner; 

	private Random rand;

	public ClimberRunnable(Climber c, Thread owner) {
		super();
		this.c = c;
		this.owner = owner;
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
		owner.interrupt();
	}
}
