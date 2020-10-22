package edu.uwf.acp.project4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameRunnable implements Runnable {
	private static final int STATE_RUNNING = 0;
	private static final int STATE_DRAW = 1;
	private static final int STATE_WINNING = 2;
	private static final int STATE_ABANDONED = 3;
	private int gameState;
	
	private int playerIndex;
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private GameController controller;
	private int winningIndex;

	public GameRunnable(Socket aSocket, GameController aGame, int index) {
		s = aSocket;
		controller = aGame;
		playerIndex = index;
		gameState = STATE_RUNNING;
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
		while (gameState == STATE_RUNNING) {
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
		System.out.println("**Command is [" + command[0] + "]" + " by player " + playerIndex);

		switch (command[0].toLowerCase()) {
		case "board":
			sendMsg("board " + controller.boardString());
			return;
		case "status":
			if (gameState == STATE_WINNING) {
				sendMsg("win " + winningIndex);
				return;
			}
			if (gameState == STATE_DRAW) {
				sendMsg("draw");
				return;
			}
			// else gameState == STATE_RUNNING
			sendMsg("mover " + controller.getMover());
			return;
		case "move":
			playerIndex = Integer.parseInt(command[1]);
			int x = Integer.parseInt(command[2]);
			int y = Integer.parseInt(command[3]);
			System.out.println("Move is player " + playerIndex + " x = " + x + " y = " + y);
			try {
				// Check legality
				if (!controller.move(playerIndex, x, y)) {
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
		if (winningIndex != -1) {
			gameState = STATE_WINNING;
		}
		if (controller.getTotalMoves() < 9) {
			System.out.println("Game continues");
			gameState = STATE_RUNNING;
		} else {
			System.out.println("Draw");
			gameState = STATE_DRAW;
		}
	}

}
