package edu.uwf.cd.acp.project3;

import java.util.Random;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ClimberGraphicsRunnable implements Runnable {
	private static final int xOff = 10;
	private static final int yOff = 20;
	private static final int ladderWidth = 80;

	private Climber climber;
	private Canvas canvas;
	private GraphicsContext gc;

	private Random rand;

	public ClimberGraphicsRunnable(Climber cl) {
		super();
		this.climber = cl;
		this.canvas = null;
		rand = new Random();
	}

	public Climber getClimber() {
		return climber;
	}

	public void setCanvas(Canvas c) {
		this.canvas = c;
		this.gc = canvas.getGraphicsContext2D();
		gc.setFont(new Font("Verdana", 12));
	}

	@Override
	public String toString() {
		return "ClimberRunnable [cl=" + climber.getName() + "]";
	}

	private void paintLadder() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.RED);
		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.BLACK);
		gc.strokeLine(xOff, yOff, xOff, yOff + 600);
		gc.strokeLine(xOff + ladderWidth, yOff, xOff + ladderWidth, yOff + 600);
		for (int i = 1; i <= 20; i++) {
			gc.strokeLine(xOff, yOff + 600 - 30 * i, xOff + ladderWidth, yOff + 600 - 30 * i);
		}
		
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        gc.fillText(
            climber.getName(), 
            Math.round(canvas.getWidth()  / 2), 
            655
        );
	}

	private void paintFigure(int rung) {
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

	public void reset() {
		climber.reset();
		paintLadder();
		paintFigure(0);
	}
	
	@Override
	public void run() {
		try {
			do {
				climber.climb();
				System.out.println("Climber " + climber.getName() + " up to " + climber.getCurrentRung());
				paintLadder();
				paintFigure(climber.getCurrentRung());
				if (!climber.done()) {
					Thread.sleep(rand.nextInt(1000));
				}
			} while (!climber.done());
		} catch (InterruptedException e) {
			System.out.println("Climber " + climber.getName() + " interrupted. Exiting.");
			return;
		}
		Score.register(this);
		System.out.println("Climber " + climber.getName() + " made it.");
	}
}
