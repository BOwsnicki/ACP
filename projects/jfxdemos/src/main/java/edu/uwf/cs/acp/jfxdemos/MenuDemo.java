package edu.uwf.cs.acp.jfxdemos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;

public class MenuDemo extends Application {

	@Override
	public void start(Stage stage) {

		// Create MenuBar
		MenuBar menuBar = new MenuBar();

		// Create menus
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu helpMenu = new Menu("Help");

		// Create MenuItems
		MenuItem newItem = new MenuItem("New");
		MenuItem openFileItem = new MenuItem("Open File");
		MenuItem exitItem = new MenuItem("Exit");

		MenuItem copyItem = new MenuItem("Copy");
		MenuItem pasteItem = new MenuItem("Paste");

		EventHandler<ActionEvent> action = theHandler();

		newItem.setOnAction(action);
		openFileItem.setOnAction(action);
		exitItem.setOnAction(action);
		copyItem.setOnAction(action);
		pasteItem.setOnAction(action);

		// Add menuItems to the Menus
		fileMenu.getItems().addAll(newItem, openFileItem, exitItem);
		editMenu.getItems().addAll(copyItem, pasteItem);

		// Add Menus to the MenuBar
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		Scene scene = new Scene(root, 350, 200);

		stage.setTitle("JavaFX Menu");
		stage.setScene(scene);
		stage.show();
	}

	private EventHandler<ActionEvent> theHandler() {
		return new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				MenuItem mItem = (MenuItem) event.getSource();
				String item = mItem.getText();
				if (item.equalsIgnoreCase("Open File")) {
					System.out.println("Open");
				} else if (item.equalsIgnoreCase("New")) {
					System.out.println("New");
				} else if (item.equalsIgnoreCase("Copy")) {
					System.out.println("Copy");
				} else if (item.equalsIgnoreCase("Exit")) {
					System.out.println("Exit");
					Platform.exit();
				}
			}
		};
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}