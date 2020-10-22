package edu.uwf.acp.project4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameRunnable implements Runnable {
	private int playerIndex;
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private GameController controller;
	private int x;
	private int y;
	private int winningIndex;
	private boolean draw = false;

	public GameRunnable(Socket aSocket, GameController aGame, int index) {
		s = aSocket;
		controller = aGame;
		playerIndex = index;
	}

	public void run() {
		try {
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			doAction();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (Exception e) {
			}
		}
	}

	public void doAction() throws IOException {
		while (true) {
			if (!in.hasNext())
				return;
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

	/**
	 * Executes a single command.
	 * 
	 * @param command the command to execute
	 */
	public void executeCommand(String[] command) {
		winningIndex = -1;
		System.out.println("**Command is [" + command[0] + "]" + " by player " + playerIndex);

		switch (command[0]) {
		case "board":
			sendMsg("board " + controller.boardString());
			return;
		case "status":
			if (winningIndex != -1) {
				sendMsg("win " + winningIndex);
				return;
			}
			if (draw) {
				sendMsg("draw");
				return;
			}
			sendMsg("mover " + controller.getMover());
			return;
		case "move":
			playerIndex = Integer.parseInt(command[1]);
			x = Integer.parseInt(command[2]);
			y = Integer.parseInt(command[3]);
			System.out.println("Move is player " + playerIndex + " x = " + x + " y = " + y);
			try {
				boolean legal = controller.move(playerIndex, x, y);
				if (!legal) {
					sendMsg("Invalid move");
					return;
				} else {
					sendMsg("OK");
				}
				controller.showBoard();
			} catch (WrongTurnException w) {
				sendMsg("Wrong turn.");
			}
			break;
		default:
			sendMsg("Invalid command");
			return;
		}

		System.out.println("checking for win ...");
		winningIndex = controller.checkForWin();
		System.out.println("Win check: " + winningIndex);

		if (controller.getTotalMoves() < 9) {
			System.out.println("Game continues");
		} else {
			System.out.println("Draw");
			draw = true;
		}
	}

}
