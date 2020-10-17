package edu.uwf.acp.project4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TicClientGUI extends Application {
	int[][] board = new int[3][3];
	Rectangle[][] boardRects = new Rectangle[3][3];

	boolean player1;

	ClientCommunication c;
	ReentrantLock moveLock = new ReentrantLock();
	Condition moveMade = moveLock.newCondition();

	@Override
	public void start(Stage stage) {
		c = new ClientCommunication(board, this);
		Thread t = new Thread(c);
		t.start();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		moveLock.lock();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				int x = 100 * j;
				int y = 100 * i;
				// System.out.println("x = " + x + " y = " + y);
				Rectangle r = new Rectangle(x, y, 100, 100);
				boardRects[i][j] = r;
				r.setFill(Color.WHITE);
				r.setStroke(Color.BLACK);
				grid.add(r, x, y);
			}

		Group root = new Group();
		root.getChildren().add(grid);
		Scene scene = new Scene(root, 350, 450);

		stage.setScene(scene);
		stage.setX(1300);
		if (player1) {
			stage.setTitle("Tic Tac Toe Player 1");
			stage.setY(50);
		} else {
			stage.setTitle("Tic Tac Toe Player 2");
			stage.setY(550);
		}
		stage.show();

		moveLock.unlock();
	}

	public boolean makeMove(int x, int y) {
		if (c.myMove) {
			if (boardRects[x][y].getFill() == Color.WHITE) {
				System.out.println("**********got legal move*********");
				moveLock.lock();
				if (player1) {
					System.out.println("I am player 1");
					boardRects[x][y].setFill(Color.BLUE);
					setCell(boardRects[x][y], 1);
				} else {
					System.out.println("I am player 2");
					boardRects[x][y].setFill(Color.RED);
					setCell(boardRects[x][y], 2);
				}
				showBoard();
				moveLock.unlock();
				return true;
			} else {
				System.out.println("^^^^^^ " + x + ", " + y + " is taken ^^^^^^^^^^");
				return false;
			}
		} else {
			System.out.println("!!!!!!!!!!!!!!!! Not your move !!!!!!!!!!!");
			return false;
		}
	}

	public void setCell(Rectangle r, int player) {
		int x = (int) r.getY() / 100;
		int y = (int) r.getX() / 100;
		board[x][y] = player;
		c.setMove(x, y);
		moveMade.signalAll();
	}

	public void setOtherPlayerMove(int x, int y) {
		if (player1) {
			boardRects[x][y].setFill(Color.RED);
		} else {
			boardRects[x][y].setFill(Color.BLUE);
		}

	}

	public void showBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	public void setPlayer1(boolean isPlayer1) {
		player1 = isPlayer1;
	}

	public static void main(String args[]) {
		launch(args);
	}
}