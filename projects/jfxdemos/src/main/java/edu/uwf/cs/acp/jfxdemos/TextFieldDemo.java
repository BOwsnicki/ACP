package edu.uwf.cs.acp.jfxdemos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextFieldDemo extends Application {

	@Override
	public void start(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, 500, 700);
		stage.setScene(scene);
		stage.setTitle("Text Field Example");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		scene.setRoot(grid);

		final TextArea name = new TextArea();
		name.setPrefColumnCount(40);
		name.setPrefRowCount(40);
		name.getText();
		GridPane.setConstraints(name, 0, 0);
		grid.getChildren().add(name);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}