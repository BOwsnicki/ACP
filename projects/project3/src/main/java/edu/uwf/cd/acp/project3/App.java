package edu.uwf.cd.acp.project3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

// import java.util.concurrent.locks.ReentrantLock;

public class App extends Application {
	private static final int NUM_CLIMBERS = 5;
	private static final int xOff = 10;
	private static final int yOff = 20;
	private static final int ladderWidth = 80;
	private List<Thread> threadList;
	
    public static void main(String[] args) {
        launch(args);
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
        	gc.strokeLine(xOff, yOff+600-30*i, xOff + ladderWidth, yOff + 600-30*i);
        }
    }    
    
    private void paintFigure(Canvas c, int rung) {
    	GraphicsContext gc = c.getGraphicsContext2D();
    	gc.setStroke(Color.GREEN);
    	gc.strokeOval(xOff + ladderWidth/2-5, yOff + 585-30*rung, 10, 10);
    	gc.strokeLine(xOff + ladderWidth/2, yOff + 595-30*rung, xOff + ladderWidth/2, yOff + 615-30*rung);
    	gc.strokeLine(xOff + ladderWidth/2, yOff + 610-30*rung, xOff + ladderWidth/2+10, yOff + 600-30*rung);
    	gc.strokeLine(xOff + ladderWidth/2, yOff + 610-30*rung, xOff + ladderWidth/2-10, yOff + 600-30*rung);
    	gc.strokeLine(xOff + ladderWidth/2, yOff + 615-30*rung, xOff + ladderWidth/2+8, yOff + 625-30*rung);
    	gc.strokeLine(xOff + ladderWidth/2, yOff + 615-30*rung, xOff + ladderWidth/2-8, yOff + 625-30*rung);
    }
    
   // @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Climbing Competition");
        Group root = new Group();
        GridPane outer = new GridPane();
        GridPane grid = new GridPane();
        for(int i = 1; i <= NUM_CLIMBERS;i++)
        { 
           Canvas c = new Canvas(100, 640); 
           grid.add(c,i,0);
           paintLadder(c);
           paintFigure(c,i); 
        }
         
       Button startButton = new Button("Start Race");
       // EventHandler<ActionEvent> start = runRace();
       // startButton.setOnAction(start);       
       
       Button resetButton = new Button("Reset Race");
       // EventHandler<ActionEvent> reset = resetRace();
       // resetButton.setOnAction(reset);  
 
       Button exitButton = new Button("Exit Race");
       // EventHandler<ActionEvent> exit = exitRace();
       // exitButton.setOnAction(exit);  
 
       HBox hbox = new HBox(30);
       hbox.setPrefWidth(500);
       hbox.setPadding(new Insets(15, 12, 15, 12));
       hbox.getChildren().addAll(startButton, resetButton, exitButton);
       grid.add(hbox, NUM_CLIMBERS + 1, 0); 
      
       outer.add(grid, 0, 1);
       outer.add(hbox, 0, 2);
       root.getChildren().add(outer);
       // root.getChildren().add(hbox);
       
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } 
    
    public void runAll(List<Climber> cList) {
		Thread myself = Thread.currentThread();	
		List<Thread> threadList = new ArrayList<>();
	
		cList.forEach(c -> {
			threadList.add(new Thread(new ClimberGraphicsRunnable(c,myself)));
		});
		
		threadList.forEach(t -> {t.start();});
		
		synchronized (myself) {
			try {
				myself.wait();
			} catch (InterruptedException e) {
				threadList.forEach(t -> {if (t.isAlive()) 
											{t.interrupt();}});
				
			}
		}
	}
}