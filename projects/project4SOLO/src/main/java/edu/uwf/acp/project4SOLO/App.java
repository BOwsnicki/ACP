package edu.uwf.acp.project4SOLO;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class App extends Application {

	private static final int READY = 1;
	private static final int RUNNING = 2;
	private static final int TERMINATED = 3;

	private static final String host = "127.0.0.1";
	private int port = 5000;
	private BufferedReader stdIn;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	private Canvas mainCanvas;
	private int status;

	public static void main(String[] args) {
		launch(args);
	}
	
	private void drawSymbol (String sym, GraphicsContext gc, int x, int y) {
		Text box = new Text(sym);
		double width = box.getLayoutBounds().getWidth();
		double height = box.getLayoutBounds().getHeight();
		// gc.fillText(sym, x*100+100-(int)width, y*100+100-(int)height);
		gc.fillText(sym, x*100+25, (y+1)*100-25);
	}
	
	private void drawBoard(String board) {
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		gc.setFont(new Font("Courier", 72));
		gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

		gc.strokeLine(0,100,300,100);
		gc.strokeLine(0,200,300,200);
		gc.strokeLine(100,0,100,300);
		gc.strokeLine(200,0,200,300);
		drawSymbol("O",gc,0,2);
		drawSymbol("X",gc,2,0);
/*		
		gc.strokeLine(xOff + ladderWidth, yOff, xOff + ladderWidth, yOff + 600);
		for (int i = 1; i <= 20; i++) {
			gc.strokeLine(xOff, yOff + 600 - 30 * i, xOff + ladderWidth, yOff + 600 - 30 * i);
		}

		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);

		gc.fillText(climber.getName(), Math.round(canvas.getWidth() / 2), 655);
		*/
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX TTT");
		Group root = new Group();
		GridPane outer = new GridPane();
		GridPane grid = new GridPane();
		int column = 1;
		mainCanvas = new Canvas(300, 300);
		grid.add(mainCanvas, 1, 0);
		status = READY;

		Button startButton = new Button("Start");
		EventHandler<ActionEvent> start = connectAndPlay();
		startButton.setOnAction(start);

		HBox hbox = new HBox(30);
		hbox.setPrefWidth(300);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.getChildren().add(startButton);

		outer.add(grid, 0, 1);
		outer.add(hbox, 0, 2);
		root.getChildren().add(outer);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void play() {
		drawBoard("");
	}
	
	public EventHandler<ActionEvent> connectAndPlay() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Connecting to host " + host + " on port " + port + ".");
		        socket = null;
		        out = null;
		        in = null;

		        try {
		              socket = new Socket(host, port);
		              out = new PrintWriter(socket.getOutputStream(), true);
		              in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		          } catch (UnknownHostException e) {
		              System.out.println("Unknown host: " + host);
		              System.exit(1);
		          } catch (IOException e) {
		              System.out.println("Unable to get streams from server");
		              e.printStackTrace();
		              System.exit(1);
		          }
		        	play();
				}
			};
		}
}