package edu.uwf.acp.jfxclients;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientApp extends Application {

	private Client c;
	
	@Override
	public void start(Stage stage) {
		c = new Client();
		Group root = new Group();
		Scene scene = new Scene(root, 500, 200);
		stage.setScene(scene);
		stage.setTitle("Text Field Example");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		root.getChildren().add(grid);
		// scene.setRoot(grid);

		final TextArea name = new TextArea();
		name.setPrefColumnCount(40);
		name.setPrefRowCount(2);
		name.getText();
		GridPane.setConstraints(name, 0, 0);
		grid.add(name,0,1);

		final Button btn = new Button();
		btn.setText("Send");
		grid.add(btn,0,2);
		
		final Label response = new Label();
		response.setText("Server: Hello");
		grid.add(response,0,3);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				response.setText(c.sendString(name.getText()));
			}
		});
		
		stage.show();
	}
	
	@Override
	public void stop() {
		c.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}