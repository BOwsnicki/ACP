package edu.uwf.cs.acp.jfxlissajous;

import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;


public class App extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }
 
	private Polyline drawFigure() {
		// x = A*cos(alpha*t + delta)
		// y = B*sin(beta*t);
		final double incr = 0.05;
		final double scale = 300;
		final double xOff = 300;
		final double yOff = 300;
		Polyline pl = new Polyline();
		ObservableList<Double> coors = pl.getPoints();
		
		double A = 0.9; // 1.0
		double alpha = 5; // 1.0
		double delta = Math.PI/4; //0.0
		double B = 0.9; // 1.0
		double beta = 4; // 1.0
		double t = 0;
		
		double x = A*Math.cos(alpha*t + delta);
		double y = B*Math.sin(beta*t);
		
		coors.add(x*scale + xOff);
		coors.add(y*scale + yOff);
		
		while (t < 24.0*Math.PI) {
			t += incr;
			x = A*Math.cos(alpha*t + delta);
			y = B*Math.sin(beta*t);
			coors.add(x*scale + xOff);
			coors.add(y*scale + yOff);
		}
		return pl;
	}
	// @Override
    public void start(Stage primaryStage) {
        Polyline pl = drawFigure();
        Group root = new Group(pl); 
        
        //Creating a scene object 
        Scene scene = new Scene(root, 600, 600);  
        
        //Setting title to the Stage 
        primaryStage.setTitle("Lissajous Curves"); 
           
        //Adding scene to the stage 
        primaryStage.setScene(scene); 
           
        //Displaying the contents of the stage 
        primaryStage.show(); 
    }    

} 