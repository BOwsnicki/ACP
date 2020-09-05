package edu.uwf.cs.dsa.project2;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.util.HashSet;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;

import edu.uwf.cs.dsa.project2.dictionary.Dictionary;
import edu.uwf.cs.dsa.project2.dictionary.LanguageSettings;
import edu.uwf.cs.dsa.project2.dictionary.Suggestions;

import java.util.Iterator;
import java.util.Locale;

import javafx.stage.FileChooser;
import javafx.application.Platform;

public class App extends Application {

	private Locale currentLocale;
	
	public App() {
		currentLocale = LanguageSettings.US_EN;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	private TextArea theText;
	private Stage stage;
	private Set<String> suggestions = new HashSet<String>();
	final int chars = 26;
	private Dictionary d;

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		primaryStage.setTitle("Title");
		Group root = new Group();
		Scene scene = new Scene(root, 500, 700, Color.WHITE);

		MenuBar menuBar = new MenuBar();
		EventHandler<ActionEvent> action = changeTabPlacement();

		Menu file = new Menu("File");

		MenuItem open = new MenuItem("Open");
		open.setOnAction(action);
		file.getItems().add(open);

		MenuItem save = new MenuItem("Save");
		save.setOnAction(action);
		file.getItems().add(save);

		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(action);
		file.getItems().add(exit);

		Menu edit = new Menu("Edit");

		MenuItem check = new MenuItem("Spell Check");
		check.setOnAction(action);
		edit.getItems().add(check);

		menuBar.getMenus().add(file);
		menuBar.getMenus().add(edit);

		BorderPane borderPane = new BorderPane();

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		borderPane.setTop(menuBar);

		theText = new TextArea();
		theText.setPromptText("Enter your first name.");
		theText.setPrefColumnCount(40);
		theText.setPrefRowCount(40);
		theText.setWrapText(true);
		theText.getText();

		borderPane.setCenter(theText);

		root.getChildren().add(borderPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		d = new Dictionary(currentLocale);
	}

	private EventHandler<ActionEvent> changeTabPlacement() {
		return new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				MenuItem mItem = (MenuItem) event.getSource();
				String item = mItem.getText();
				if (item.equalsIgnoreCase("Open")) {
					System.out.println("Open");
					openFile();
				} else if (item.equalsIgnoreCase("Spell Check")) {
					spellCheck();
				} else if (item.equalsIgnoreCase("Save")) {
					System.out.println("Save");
					saveFile();
				} else if (item.equalsIgnoreCase("Exit")) {
					System.out.println("Exit");
					Platform.exit();
				}
			}
		};
	}

	public void saveFile() {
		System.out.println("^^^^^^^^^^^in save ^^^");
		final FileChooser fileChooser = new FileChooser();
		String current = "";
		try {
			current = new java.io.File(".").getCanonicalPath();
		} catch (Exception e) {
		}
		File userDirectory = new File(current);
		fileChooser.setInitialDirectory(userDirectory);

		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			String st = theText.getText();
			saveTextToFile(st, file);
		}
	}

	private void saveTextToFile(String content, File file) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(content);
			writer.close();
		} catch (IOException ex) {
			System.out.println("error saving file");
		}
	}

	public void openFile() {
		final FileChooser fileChooser = new FileChooser();
		String current = "";
		try {
			current = new java.io.File(".").getCanonicalPath();
		} catch (Exception e) {
		}
		File userDirectory = new File(current);
		fileChooser.setInitialDirectory(userDirectory);

		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			String st = new String();
			try {
				Scanner s = new Scanner(file);
				while (s.hasNextLine())
					st += s.nextLine();
				theText.setText(st);
			} catch (Exception e) {
			}
		}
	}
	
	public void spellCheck() {
		String st = theText.getText();
		st = st.replace('.', ' ');
		st = st.replace(',', ' ');
		st = st.replace('?', ' ');
		System.out.println("**" + st);
		String[] tokens = st.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].length() > 0) {
				String word = tokens[i].toLowerCase();
				boolean isThere = d.lookup(word);
				if (!isThere) {
					// theText.selectRange(pos, pos+tokens[i].length()); // added for highlight
					suggestions = Suggestions.getSuggestions(word);
					showMisspelled(word);
				}
				// else
				// pos += tokens[i].length()+1;
			}
		}
	}
	public String showMisspelled(String word) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		String s = "Misspelling: " + word + '\n';

		Iterator<String> it = suggestions.iterator();
		while (it.hasNext())
			s = s + it.next() + '\n';
		alert.setContentText(s);

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			System.out.println("OK");
		}
		return "hello";
	}
}