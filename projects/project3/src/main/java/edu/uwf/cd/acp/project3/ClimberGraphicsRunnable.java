package edu.uwf.cd.acp.project3;

import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClimberGraphicsRunnable implements Runnable {
	private static final int xOff = 10;
	private static final int yOff = 20;
	private static final int ladderWidth = 80;

	private Climber cl;
	private Canvas c;
	private Thread owner;

	private Random rand;

	public ClimberGraphicsRunnable(Climber cl, Canvas c, Thread owner) {
		super();
		this.cl = cl;
		this.c = c;
		this.owner = owner;
		rand = new Random();
	}

	public Climber getCl() {
		return cl;
	}

	public void setCl(Climber cl) {
		this.cl = cl;
	}

	public void setC(Canvas c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "ClimberRunnable [cl=" + cl.getName() + "]";
	}

	private void paintLadder(Canvas c) {
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
		gc.setStroke(Color.RED);
		gc.strokeRect(0, 0, c.getWidth(), c.getHeight());

		gc.setStroke(Color.BLACK);
		gc.strokeLine(xOff, yOff, xOff, yOff + 600);
		gc.strokeLine(xOff + ladderWidth, yOff, xOff + ladderWidth, yOff + 600);
		for (int i = 1; i <= 20; i++) {
			gc.strokeLine(xOff, yOff + 600 - 30 * i, xOff + ladderWidth, yOff + 600 - 30 * i);
		}
	}

	private void paintFigure(Canvas c, int rung) {
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.setStroke(Color.GREEN);
		gc.strokeOval(xOff + ladderWidth / 2 - 5, yOff + 585 - 30 * rung, 10, 10);
		gc.strokeLine(xOff + ladderWidth / 2, yOff + 595 - 30 * rung, xOff + ladderWidth / 2, yOff + 615 - 30 * rung);
		gc.strokeLine(xOff + ladderWidth / 2, yOff + 610 - 30 * rung, xOff + ladderWidth / 2 + 10,
				yOff + 600 - 30 * rung);
		gc.strokeLine(xOff + ladderWidth / 2, yOff + 610 - 30 * rung, xOff + ladderWidth / 2 - 10,
				yOff + 600 - 30 * rung);
		gc.strokeLine(xOff + ladderWidth / 2, yOff + 615 - 30 * rung, xOff + ladderWidth / 2 + 8,
				yOff + 625 - 30 * rung);
		gc.strokeLine(xOff + ladderWidth / 2, yOff + 615 - 30 * rung, xOff + ladderWidth / 2 - 8,
				yOff + 625 - 30 * rung);
	}

	@Override
	public void run() {
		try {
			do {
				cl.climb();
				System.out.println("Climber " + cl.getName() + " up to " + cl.getCurrentRung());
				paintLadder(c);
				paintFigure(c,cl.getCurrentRung());
				if (!cl.done()) {
					Thread.sleep(rand.nextInt(500));
				}
			} while (!cl.done());
		} catch (InterruptedException e) {
			System.out.println("Climber " + cl.getName() + " interrupted. Exiting.");
			return;
		}
		System.out.println("Climber " + cl.getName() + " made it.");
		owner.interrupt();
	}
}
