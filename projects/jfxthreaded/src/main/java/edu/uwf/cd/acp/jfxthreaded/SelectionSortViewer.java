package edu.uwf.cd.acp.jfxthreaded;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class SelectionSortViewer extends Application {

    private Stage stage;
    private Canvas c;
    private SelectionSortComponent ssc;

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Selection Sorter");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
      
        c = new Canvas(500,500);
        ssc = new SelectionSortComponent(c);
        
        root.getChildren().add(c);
        primaryStage.setScene(scene);
        primaryStage.show();
        ssc.startAnimation();
    }

}