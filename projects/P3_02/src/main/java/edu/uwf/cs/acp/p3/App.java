package edu.uwf.cs.acp.p3;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

	private static final double xScale = 100.0;
	private static final double yScale = 100.0;
	private static final double xOff = 50.0;
	private static final double yOff = 50.0;
	private void store(ObservableList<Double> coors, double x, double y) {
		coors.add(xOff + xScale*x);
		coors.add(yOff + 600.0-yScale*y);
	}
	private void setCoors(Line l, double xStart, double yStart, double xEnd, double yEnd) {
		l.setStartX(xOff + xScale*xStart);
		l.setStartY(yOff + 600.0-yScale*yStart);
		l.setEndX(xOff + xScale*xEnd);
		l.setEndY(yOff + 600.0-yScale*yEnd);	
	}
	
    @Override
    public void start(Stage primaryStage) {
    	Polyline axes = new Polyline();
    	ObservableList<Double> coors = axes.getPoints();
    	
    	double[] results = TryMatrix.results();
    	// double[] results = {1.0, 2.0, 2.7, 3.3, 3.6, 3.8};
    	store(coors,0.0,6.0);
    	store(coors,0.0,0.0);
    	store(coors,results.length,0.0);
    	
    	Polyline graph = new Polyline();
    	coors = graph.getPoints();
    	for (int i = 0; i < results.length; i++) {
    		store(coors,i+1,results[i]);
    	}
    	Group root = new Group(axes);
    	root.getChildren().add(graph);
        for (int x = 1; x <= results.length; x++) {
        	Line l = new Line();
        	setCoors(l,x,-0.1,x,0.1);
        	root.getChildren().add(l);
        }
        for (int x = 1; x <= 6; x++) {
        	Line l = new Line();
        	setCoors(l,-0.1,x,0.1,x);
        	root.getChildren().add(l);
        }
        //Creating a scene object 
        Scene scene = new Scene(root, 1200, 700);  
        
        //Setting title to the Stage 
        primaryStage.setTitle("n = 1500, t=8"); 
           
        //Adding scene to the stage 
        primaryStage.setScene(scene); 
           
        //Displaying the contents of the stage 
        primaryStage.show(); 
    }

    public static void main(String[] args) {
        launch();
    }

}