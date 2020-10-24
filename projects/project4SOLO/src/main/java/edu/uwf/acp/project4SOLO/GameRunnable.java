package edu.uwf.acp.project4SOLO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class GameRunnable implements Runnable {
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private GameController controller;
	private String clientSymbol;
	private String mySymbol;

	public GameRunnable(Socket socket, GameController controller) {
		this.socket = socket;
		this.controller = controller;
	}

	public void run() {
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
			play();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
			}
		}
	}

	public void play() throws IOException, InterruptedException {
		while (true) {
			// if (!in.hasNext())
			// return;
			String command = in.nextLine();
			if (command.equalsIgnoreCase("quit")) {
				System.out.println("Received quit. Terminating.");
				sendMsg("quit");
				Thread.sleep(1000);
				return;
			} else {
				executeCommand(command.split(" "));
			}
		}
	}

	public void sendMsg(String msg) {
		out.println(msg);
		out.flush();
	}

	private void doMove(int index) {
		System.out.println("Move: " + index);

		// Check legality
		if (!controller.moveOther(index)) {
			sendMsg("Invalid move");
			return;
		} else {
			controller.updateState();
			if (controller.gameState == GameController.STATE_RUNNING) {
				// Good for another move
				int serverMove = controller.moveServer();
				sendMsg("server " + serverMove);
			} else {
				sendMsg("ended");
			}
		}
	}

	public void executeCommand(String[] command) {
		System.out.println("**Command is [" + command[0] + "]");

		switch (command[0].toLowerCase()) {
		case "play":
			clientSymbol = "X";
			if (new Random().nextBoolean()) {
				System.out.println("Server goes first");
				controller.moveServer();
				controller.updateState();
				clientSymbol = "O";
			}
			mySymbol = (clientSymbol.equals("X") ? "O" : "X");
			sendMsg("symbol " + clientSymbol);
			return;
		case "board":
			sendMsg("board " + controller.boardString());
			return;
		case "status":
			if (controller.gameState == GameController.STATE_WINNING) {
				sendMsg("win " + controller.winningIndex);
				return;
			}
			if (controller.gameState == GameController.STATE_DRAW) {
				sendMsg("draw");
				return;
			}
			// else controller.gameState == STATE_RUNNING
			sendMsg("move");
			return;
		case "move":
			int index = Integer.parseInt(command[1]);
			doMove(index);
			return;
		default:
			sendMsg("Invalid command");
			return;
		}
	}
}
