package edu.uwf.acp.project4;

import java.util.logging.Logger;

public class GameController {
	private static final int EMPTY = -1;
	private int[][] board = new int[3][3];
	private int playerCount = 0;
	private int totalMoves = 0;
	private Logger logger;
	private int toMove;

	GameRunnable[] players = new GameRunnable[2];

	String player1 = null;
	String player2 = null;

	public GameController(Logger logger) {
		this.logger = logger;
		this.toMove = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = EMPTY;
	}

	public int getMover() {
		return toMove;
	}
	
	public void nextMover() {
		toMove = (toMove + 1) % 2;
	}
	
	public void addPlayer(GameRunnable ga) {
		if (playerCount < 2)
			players[playerCount++] = ga;
	}

	public void sendMessageTo(String msg, int which) {
		players[which].sendMsg(msg);
	}

	public void showBoard() {
		System.out.println("The board currently");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(board[i][j]);
			System.out.println("");
		}
	}

	public String boardString() {
		String st = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				if (board[i][j] == EMPTY) {
					st += ".";
				} else {
					st += board[i][j];
				}

		}
		return st;
	}

	int getTotalMoves() {
		return totalMoves;
	}

	/**
	 * returns 0 = no winner, 1 player 1, or 2 player 2 wins
	 */
	public int checkForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkHoriz(i, 0))
				return 0;
			if (checkHoriz(i, 1))
				return 1;
			if (checkVert(i, 0))
				return 0;
			if (checkVert(i, 1))
				return 1;
		}
		if (checkFirstDiag(0))
			return 0;
		if (checkFirstDiag(1))
			return 1;
		if (checkSecondDiag(0))
			return 0;
		if (checkSecondDiag(1))
			return 1;

		return -1;
	}

	public boolean checkHoriz(int whichRow, int whichPlayer) {
		for (int j = 0; j < 3; j++)
			if (board[whichRow][j] != whichPlayer)
				return false;
		return true;
	}

	public boolean checkVert(int whichCol, int whichPlayer) {
		for (int i = 0; i < 3; i++)
			if (board[i][whichCol] != whichPlayer)
				return false;
		return true;
	}

	public boolean checkFirstDiag(int whichPlayer) {
		for (int i = 0; i < 3; i++) {
			if (board[i][i] != whichPlayer)
				return false;
		}
		return true;
	}

	public boolean checkSecondDiag(int whichPlayer) {
		for (int i = 0; i < 3; i++) {
			if (board[i][2 - i] != whichPlayer)
				return false;
		}
		return true;
	}

	public boolean move(int whichPlayer, int x, int y) throws WrongTurnException {
		System.out.println("player " + whichPlayer + " moves x = " + x + " y = " + y);
		
		if (whichPlayer != toMove) throw new WrongTurnException();
		
		if (board[x][y] != EMPTY) {
			return false; 
		}	
		board[x][y] = whichPlayer;
		totalMoves++;
		nextMover();
		return true;
	}

}