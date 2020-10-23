package edu.uwf.acp.project4SOLO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameRunnable implements Runnable {
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private GameController controller;

	public GameRunnable(Socket aSocket, GameController aGame) {
		s = aSocket;
		controller = aGame;
	}

	public void run() {
		try {
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			play();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (Exception e) {
			}
		}
	}

	public void play() throws IOException {
		while (controller.gameState == GameController.STATE_RUNNING) {
			// if (!in.hasNext())
				// return;
			String command = in.nextLine();
			if (command.equalsIgnoreCase("QUIT"))
				return;
			else
				executeCommand(command.split(" "));
		}
	}

	public void sendMsg(String msg) {
		out.println(msg);
		out.flush();
	}

	public void executeCommand(String[] command) {
		System.out.println("**Command is [" + command[0] + "]");

		switch (command[0].toLowerCase()) {
		case "play":
			sendMsg("move");
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
			System.out.println("Move: " + index);

				// Check legality
				if (!controller.moveOther(index)) {
					sendMsg("Invalid move");
					return;
				} else {
					controller.updateState();
					sendMsg("OK");
				}
				controller.showBoard();

			break;
		default:
			sendMsg("Invalid command");
			return;
		}
	}

}
