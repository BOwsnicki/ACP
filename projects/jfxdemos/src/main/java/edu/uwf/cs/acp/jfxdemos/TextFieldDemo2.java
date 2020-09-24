package edu.uwf.cs.acp.jfxdemos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextFieldDemo2 extends Application {

	@Override
	public void start(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.setTitle("Finger Exercise 2");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		scene.setRoot(grid);

		final TextArea name = new TextArea();
		name.setPrefColumnCount(40);
		name.setPrefRowCount(10);
		name.getText();

		grid.add(name,0,0);
		
		Button btn = new Button();
		btn.setText("Show me!");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println(name.getText());
			}
		});
		grid.add(btn,0,1);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}