package edu.uwf.cd.acp.project3.text;

import java.util.Random;

import edu.uwf.cd.acp.project3.classes.Climber;

public class ClimberTextRunnable implements Runnable {
	private Climber c;
	private Thread owner; 

	private Random rand;

	public ClimberTextRunnable(Climber c, Thread owner) {
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
		System.out.println("Climber " + c.getName() + " made it.");
		owner.interrupt();
	}
}
