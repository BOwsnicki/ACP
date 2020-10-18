package edu.uwf.acp.project4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameRunnable implements Runnable {
	private int playerNumber;
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private GameController game;
	private int x;
	private int y;
	private int aWinner;

	public GameRunnable(Socket aSocket, GameController aGame, int num) {
		s = aSocket;
		game = aGame;
		playerNumber = num;
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
			} catch (Exception e) {}
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
		aWinner = 0;
		System.out.println("**Command is [" + command[0] + "]" + " by player " + playerNumber);

		/*
		if (command[0].compareTo("hello") == 0) {
			if (game.player1 == null) {
				game.player1 = new String("player1");
				System.out.println("our first player is " + game.player1);
			} else if (game.player2 == null) {
				game.player2 = new String("player2");
				System.out.println("our second player is " + game.player2);
			} else {
				System.out.println(" we already have two players");
				out.println("we already have two players");
				out.flush();
				return;
			}
			out.println("new player command for player " + playerNumber);
			out.flush();
			return;
		}
		*/
		if (command[0].equals("move")) {
			playerNumber = Integer.parseInt(command[1]);
			x = Integer.parseInt(command[2]);
			y = Integer.parseInt(command[3]);
			System.out.println("Move is player " + playerNumber + " x = " + x + " y = " + y);
			try {
				boolean legal = game.move(playerNumber, x, y);
				if (!legal) {
					out.println("Invalid move");
					out.flush();
					return;
				} else {
					out.println("OK");
					out.flush();
				}
				game.showBoard();
			} catch (WrongTurnException w) {
				out.println("Wrong turn.");
				out.flush();
			}
		}	 else {
			out.println("Invalid command");
			out.flush();
			return;
		}
		System.out.println("checking for win ...");
		aWinner = game.checkForWin();
		if (aWinner > 0) {
			String msg = aWinner + " won!!!! " + x + " " + y;
			if (playerNumber == 1)
				game.sendMessageTo(msg, 1);
			else
				game.sendMessageTo(msg, 0);
			out.println(msg);
			out.flush();
			System.out.println(aWinner + " won!!!! ");
		} else if (game.getTotalMoves() < 9) {
			String msg = "game continues: " + x + " " + y;
			out.println(msg);
			out.flush();
			if (playerNumber == 1)
				game.sendMessageTo(msg, 1);
			else
				game.sendMessageTo(msg, 0);

			System.out.println("game continues");
		} else {
			String msg = "A draw " + x + " " + y;
			out.println(msg);
			out.flush();
			if (playerNumber == 1)
				game.sendMessageTo(msg, 1);
			else
				game.sendMessageTo(msg, 0);
		}
	}

}
