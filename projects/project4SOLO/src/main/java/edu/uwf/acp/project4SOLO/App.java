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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class App extends Application {
	private static final String host = "127.0.0.1";
	private static final int ME = 1;
	private static final char EMPTY_CHAR = '.';
	private static final char SERVER_CHAR = '0';
	private static final char ME_CHAR = '1';
	
	private int port = 5000;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String mySymbol;
	private String serverSymbol;
	
	private Canvas mainCanvas;

	public void sendRequest(String msg) {
	  System.out.println("sending: " + msg);
	  out.println(msg);
	  out.flush();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void drawSymbol (String sym, GraphicsContext gc, int x, int y) {
		gc.fillText(sym, x*100+25, (y+1)*100-25);
	}
	
	private void drawBoard() {
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		gc.setFont(new Font("Courier", 72));
		gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

		gc.strokeLine(0,100,300,100);
		gc.strokeLine(0,200,300,200);
		gc.strokeLine(100,0,100,300);
		gc.strokeLine(200,0,200,300);
	}

	private void drawPlayers(String board) {
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		gc.setFont(new Font("Courier", 72));
		for (int i = 0; i < 9; i++) {
			char player = board.charAt(i);
			if (player == ME_CHAR) {
				drawSymbol(mySymbol,gc,i%3,i/3);
			} else {
				if (player == SERVER_CHAR) {
					drawSymbol(serverSymbol,gc,i%3,i/3);
			}
		}
	}
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX TTT");
		Group root = new Group();
		GridPane outer = new GridPane();
		GridPane grid = new GridPane();
		mainCanvas = new Canvas(300, 300);
		EventHandler<MouseEvent> click = getCanvasClick();
		mainCanvas.setOnMouseClicked(click);
		drawBoard();
		grid.add(mainCanvas, 1, 0);

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

	private void doProlog() throws IOException {
		String command = "play";
		sendRequest(command);
	  
		String response = in.readLine();
		System.out.println("server: " + response);
	
		// What's my symbol?
		mySymbol = response.split(" ")[1];
		serverSymbol = (mySymbol.equals("X") ? "O" : "X"); 
		System.out.println("symbols: " + mySymbol + " " + serverSymbol);
	}
	
	private void play() throws IOException {
		// 1. Connect and see what's coming back
		doProlog();
        
        // 2. Get going
        boolean gameRunning = true;
   		// 2a. Check if we really have game here
   		sendRequest("status");
   		String response = in.readLine();
   		System.out.println("server: " + response);
   		switch (response.split(" ")[0]) {
   			case "draw":
   			case "win": 	gameRunning = false;
   							break;
   			default:
   		}
   		requestBoard();
      	}
	}
	
	public EventHandler<MouseEvent> getCanvasClick() {
		return new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				int x = (int)Math.round(e.getX())/100;
				int y = (int)Math.round(e.getY())/100;
				System.out.println(x + " " + y);
			}
		};
	}
	
      	private void showBoard(String board) {
    		drawBoard();
    		drawPlayers(board);      		
      	}
	
      	private void requestBoard() throws IOException {
            sendRequest("board");
            String board = in.readLine();
            System.out.println("server: " + board);
            showBoard(board.split(" ")[1]);
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
		        	try {
						play();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}
}