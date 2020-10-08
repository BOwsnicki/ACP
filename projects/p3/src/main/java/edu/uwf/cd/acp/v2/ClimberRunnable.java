package edu.uwf.cd.acp.v2;

import java.util.Random;

public class ClimberRunnable implements Runnable {
	private Climber c;
	private int xBase;
	private int yBase;
	private Thread owner; 

	private Random rand;

	public ClimberRunnable(Climber c, int xBase, int yBase, Thread owner) {
		super();
		this.c = c;
		this.xBase = xBase;
		this.yBase = yBase;
		this.owner = owner;
		rand = new Random();
	}

	public Climber getC() {
		return c;
	}

	public void setC(Climber c) {
		this.c = c;
	}

	public int getxBase() {
		return xBase;
	}

	public void setxBase(int xBase) {
		this.xBase = xBase;
	}

	public int getyBase() {
		return yBase;
	}

	public void setyBase(int yBase) {
		this.yBase = yBase;
	}

	@Override
	public String toString() {
		return "ClimberRunnable [c=" + c.getName() + ", xBase=" + xBase + ", yBase=" + yBase + "]";
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
