package edu.uwf.cd.acp.v2;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GraphicsRunner extends Application implements ClimbRunner {
	
	@Override
	public void runAll(List<Climber> cList) {
		Thread myself = Thread.currentThread();	
		List<Thread> threadList = new ArrayList<>();
	
		cList.forEach(c -> {
			threadList.add(new Thread(new ClimberRunnable(c,0,0,myself)));
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

   // @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Climbing Competition");
        Group root = new Group();
        GridPane grid = new GridPane();
        for(int i = 1; i <= 5;i++)
        { 
           Canvas c = new Canvas(850, 80); 
           grid.add(c, i, 0);
           // horses.add(new Horse(20, 20, c, i,this));
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
 
       HBox hbox = new HBox(10);
       hbox.setPrefWidth(470);
       hbox.getChildren().addAll(startButton, resetButton, exitButton);
       // grid.add(hbox, 0, numhorses+1); 
 
      
       root.getChildren().add(grid);

       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } 
    
    public static void main(String[] args) {
        launch(args);
    }
}
 