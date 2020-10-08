package edu.uwf.cd.acp.p3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.util.concurrent.locks.ReentrantLock;

public class App extends Application {
	private static final int NUM_CLIMBERS = 5;
	
    public static void main(String[] args) {
        launch(args);
    }
 
    private void paintLadder(Canvas c) {
    	int xOff = 10;
    	int yOff = 10;
        GraphicsContext gc = c.getGraphicsContext2D();
        Line left = new Line(xOff, yOff, xOff, yOff + 700);
        Line right = new Line(xOff + 70, yOff, xOff + 70, yOff + 700);
        g(gc);
    
   // @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Climbing Competition");
        Group root = new Group();
        GridPane grid = new GridPane();
        for(int i = 1; i <= NUM_CLIMBERS;i++)
        { 
           Canvas c = new Canvas(50, 800); 
           grid.add(c, i, 0);
           paintLadder(c);
//           horses.add(new Horse(20, 20, c, i,this));       
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
 
       HBox hbox = new HBox(0);
       hbox.setPrefWidth(500);
       hbox.getChildren().addAll(startButton, resetButton, exitButton);
       grid.add(hbox, 0, NUM_CLIMBERS + 1); 
      
       root.getChildren().add(grid);

       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } 
    

}