package edu.uwf.cs.acp.jfxXMLDemo;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class App extends Application
{
    private static String fxmlName = "FxFXML";
	private static String fxmlFullName = App.class.getResource(fxmlName + ".fxml").getFile();
    
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     
    @Override
    public void start(Stage stage) throws IOException
    {
        // Create the FXMLLoader 
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        FileInputStream fxmlStream = new FileInputStream(fxmlFullName);;
 
        // Create the Pane and all Details
        VBox root = (VBox) loader.load(fxmlStream);
 
        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("A FXML Example with a Controller");
        // Display the Stage
        stage.show();
         
    }
}