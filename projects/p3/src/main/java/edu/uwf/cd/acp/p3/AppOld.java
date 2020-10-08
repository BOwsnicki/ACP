package edu.uwf.cd.acp.p3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.concurrent.locks.ReentrantLock;

public class AppOld extends Application {
 
   ArrayList<Horse> horses = new ArrayList<Horse>();
   ArrayList<Thread> hThread = new ArrayList<Thread>();
   Button startButton, resetButton, exitButton;
   int numhorses = 5;
   public ReentrantLock horseLock = new ReentrantLock();

    public static void main(String[] args) {
        launch(args);
    }
 
   // @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Horse Race");
        Group root = new Group();
        GridPane grid = new GridPane();
        for(int i = 1; i <=numhorses;i++)
        { 
           Canvas c = new Canvas(850, 80); 
           grid.add(c, 0, i);
           horses.add(new Horse(20, 20, c, i,this));
        
        }
         
       startButton = new Button("Start Race");
        EventHandler<ActionEvent> start = runRace();
       startButton.setOnAction(start);       
       
       resetButton = new Button("Reset Race");
       EventHandler<ActionEvent> reset = resetRace();
       resetButton.setOnAction(reset);  
 
       exitButton = new Button("Exit Race");
       EventHandler<ActionEvent> exit = exitRace();
       exitButton.setOnAction(exit);  
 
       HBox hbox = new HBox(10);
       hbox.setPrefWidth(470);
       hbox.getChildren().addAll(startButton, resetButton, exitButton);
       grid.add(hbox, 0, numhorses+1); 
 
      
       root.getChildren().add(grid);

       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } 
    
    
    public void stopAll(int which)
    {
      for(int i = 0; i <numhorses; i++)
      {

        if(!(i+1 == which))
        {
         System.out.println("stopping i+1 = " + (i+1) + " which = " + which);
          Thread t = hThread.get(i);
          t.interrupt();
        }
      }
      if(which<6)
      {
         System.out.println("winning horse = " + which);
         // JOptionPane.showMessageDialog(null, "The winner is horse " + which); 
      }
    }
       
 
    public EventHandler<ActionEvent> runRace() {
               return new EventHandler<ActionEvent>() {
     
     public void handle(ActionEvent event) {
     System.out.println("got start click");
     hThread = new ArrayList<Thread>();
       for(int j = 0; j < horses.size(); j++)
       {
         Horse h = horses.get(j);
         Thread t = new Thread(h);
         hThread.add(t);
         t.start();
       }
     }   
    }; 
  }
  
  public EventHandler<ActionEvent> resetRace() {
       return new EventHandler<ActionEvent>() {
     
     public void handle(ActionEvent event) 
     {
       System.out.println("got reset click");
       stopAll(10);
       for(int i = 0; i <numhorses;i++)
       { 
           Horse h = horses.get(i);
           h.eraseCurrentPos();
           h.setPos(20,20);
           h.setFirst(true);
           h.draw();
        }
     }   
    }; 
  }
  
  public EventHandler<ActionEvent> exitRace() {
       return new EventHandler<ActionEvent>() {
     
     public void handle(ActionEvent event) {
       System.out.println("got exit click");
       System.exit(0);
     }   
    }; 
  }
 
}