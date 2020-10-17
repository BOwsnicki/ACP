package edu.uwf.acp.project4;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
//import javafx.scene.control.TextInputDialog; // no good not JavaFX thread
// import javax.swing.JOptionPane;
import java.util.Optional;

class ClientCommunication implements Runnable {
	String name;
	int playerNum;
	boolean myMove;
	boolean moveMade = false;
	int[][] board;
	int moveX, moveY;
	Scanner in;
	PrintWriter out;
	TicClientGUI gui;

	public ClientCommunication(int[][] theBoard, TicClientGUI tcG) {
		board = theBoard;
		gui = tcG;

		try {
			Socket s = new Socket("localhost", TicServer.TIC_PORT);
			InputStream instream = s.getInputStream();
			OutputStream outstream = s.getOutputStream();
			in = new Scanner(instream);
			out = new PrintWriter(outstream);
		} catch (Exception e) {
		}
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void sleepABit(int howLong) {
		try {
			Thread.sleep(howLong);
		} catch (Exception e) {
		}
	}

	public void run() {
		String command = "hello\n";
		System.out.print("Sending: " + command);
		out.print(command);
		out.flush();
		String response = in.nextLine();
		System.out.println("Receiving: " + response);
		if (response.contains("player 1")) {
			playerNum = 1;
			gui.setPlayer1(true);
			myMove = true;
			System.out.println("in player 1 response = " + response);
			sleepABit(1000);
		} else {
			playerNum = 2;
			gui.setPlayer1(false);
			System.out.println("in player 2 response = " + response);
			response = in.nextLine();
			System.out.println("player 1 first move = " + response);
			String[] resp = response.split(" ");
			for (int i = 0; i < resp.length; i++)
				System.out.println("&&&&& " + resp[i]);
			int x = Integer.parseInt(resp[2]);
			int y = Integer.parseInt(resp[3]);
			sleepABit(500);
			gui.setOtherPlayerMove(x, y);
			sleepABit(500);
			myMove = false;

		}
		play();
	}

	public void play() {
		Random rand = new Random();
		String command, response;
		while (true) {
			myMove = true;
			gui.moveLock.lock();
			try {
				while (!moveMade) {
					int x = rand.nextInt(3);
					int y = rand.nextInt(3);
					moveMade = gui.makeMove(x, y);
				}
				sleepABit(1000);
			} catch (Exception e) {
			}
			command = "move " + playerNum;
			command += " " + moveX + " " + moveY;
			command += "\n";

			System.out.print("Sending move: " + command);

			out.print(command);
			out.flush();
			moveMade = false;
			gui.moveLock.unlock();
			response = in.nextLine();
			System.out.println("Receiving (mine): " + response);

			if (response.contains("won") || response.contains("draw")) {
				System.out.println("response following mine = " + response);
				return;
			}
			myMove = false;
			response = in.nextLine();

			System.out.println("Receiving (other): " + response);
			if (response.contains("won") || response.contains("draw")) {
				String[] resp = response.split(" ");
				int x = Integer.parseInt(resp[2]);
				int y = Integer.parseInt(resp[3]);
				System.out.println("response following other = " + response);
				gui.setOtherPlayerMove(x, y);
				if (response.contains("draw"))
					System.out.println("The game was a draw");
				else if (playerNum == 1)
					System.out.println("Red Won!!");
				else
					System.out.println("Blue Won!!");
				return;
			} else {
				String[] resp = response.split(" ");
				int x = Integer.parseInt(resp[2]);
				int y = Integer.parseInt(resp[3]);
				gui.setOtherPlayerMove(x, y);
				sleepABit(1000);
			}
		}
	}

	public void setMove(int x, int y) {
		System.out.println("setting current move x = " + x + " y = " + y);
		moveX = x;
		moveY = y;
		moveMade = true;
	}
}