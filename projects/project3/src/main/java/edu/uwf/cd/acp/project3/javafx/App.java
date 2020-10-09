package edu.uwf.cd.acp.project3.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import edu.uwf.cd.acp.project3.classes.Climber;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class App extends Application {

	private static final int READY = 1;
	private static final int RUNNING = 2;
	private static final int TERMINATED = 3;

	private List<ClimberGraphicsRunnable> runnableList = new ArrayList<>();
	private List<Thread> threadList = new ArrayList<>();
	private int status;

	private void buildList() {
		List<Climber> cList = new ArrayList<>();
		cList.add(new Climber("Harry", 20));
		cList.add(new Climber("Helen", 20));
		cList.add(new Climber("Holly", 20));
		cList.add(new Climber("Henry", 20));
		cList.add(new Climber("Heidi", 20));

		cList.forEach(c -> {
			runnableList.add(new ClimberGraphicsRunnable(c));
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX Climbing Competition");
		Group root = new Group();
		GridPane outer = new GridPane();
		GridPane grid = new GridPane();
		int column = 1;
		buildList();
		for (ClimberGraphicsRunnable r : runnableList) {
			Canvas c = new Canvas(100, 670);
			grid.add(c, column++, 0);
			r.setCanvas(c);
			r.reset();
			threadList.add(new Thread(r));
		}
		status = READY;

		Button startButton = new Button("Start Climbing!");
		EventHandler<ActionEvent> start = runRace();
		startButton.setOnAction(start);

		Button resultButton = new Button("Result");
		EventHandler<ActionEvent> result = resultRace();
		resultButton.setOnAction(result);

		Button resetButton = new Button("Reset");
		EventHandler<ActionEvent> reset = resetRace();
		resetButton.setOnAction(reset);

		HBox hbox = new HBox(30);
		hbox.setPrefWidth(500);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.getChildren().addAll(startButton, resultButton, resetButton);

		outer.add(grid, 0, 1);
		outer.add(hbox, 0, 2);
		root.getChildren().add(outer);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public EventHandler<ActionEvent> resetRace() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				threadList.clear();
				runnableList.forEach(r -> {
					r.reset();
					threadList.add(new Thread(r));
				});
				status = READY;
			}
		};
	}

	private boolean noRunningThread() {
		for (Thread t : threadList) {
			if (t.getState() != Thread.State.TERMINATED && t.getState() != Thread.State.NEW) {
				return false;
			}
		}
		return true;
	}

	public EventHandler<ActionEvent> resultRace() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (noRunningThread()) {
					if (status == RUNNING) {
						status = TERMINATED;
						Alert a = new Alert(AlertType.NONE, "", ButtonType.APPLY);
						String s = "Final Results:\n\n";
						Runnable[] result = Score.standings();
						for (int i = 0; i < result.length; i++) {
							s += "" + (i + 1) + ": " + ((ClimberGraphicsRunnable) result[i]).getClimber().getName()
									+ "\n";
						}
						a.setContentText(s);
						a.show();
					} else {
						Alert a = new Alert(AlertType.ERROR);
						a.setContentText("Competition not started!");
						a.show();						
					}
				} else {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Competition still underway!");
					a.show();
				}
			}
		};
	}

	public EventHandler<ActionEvent> runRace() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (status != READY) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Competitors not ready!");
					a.show();
				} else {
					Score.reset();
					System.out.println("HERE WE GO!");
					threadList.forEach(t -> {
						t.start();
					});
					status = RUNNING;
				}
			}
		};
	}
}