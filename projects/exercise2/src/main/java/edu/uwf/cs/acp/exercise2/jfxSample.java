package edu.uwf.cs.acp.exercise2;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class jfxSample extends Application {
	Button button;
	TextArea textArea;
	
	public static void main(String[] args) {launch(args);}
	@Override
	public void start(Stage primaryStage)throws Exception{
		primaryStage.setTitle("Write'n Print");
		
		textArea = new TextArea();
		textArea.setPrefSize(60, 100);
		Insets insets = new Insets(10);
		
		
		button = new Button("Print-it!");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(textArea.getText());
			}	
					});
		
		
		VBox vbox  = new VBox(10, textArea, button);
		vbox.setPadding(insets);
				
		Scene scene = new Scene(vbox, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}