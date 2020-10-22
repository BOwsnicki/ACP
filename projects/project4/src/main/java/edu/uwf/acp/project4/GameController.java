package edu.uwf.acp.project4;

import java.util.logging.Logger;

public class GameController {
	private static final int EMPTY = -1;
	private static final int[][] WIN_CHECKS = 
		{
			{0,1,2}, {3,4,5}, {6,7,8},
			{0,3,6}, {1,4,7}, {2,5,8},
			{0,4,8}, {2,4,6}
	};
	private int[] board = new int[9];
	private int playerCount = 0;
	private int totalMoves = 0;
	private Logger logger;
	private int toMove;

	GameRunnable[] players = new GameRunnable[2];

	public GameController(Logger logger) {
		this.logger = logger;
		this.toMove = 0;
		for (int i = 0; i < 9; i++)
				board[i] = EMPTY;
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

	public void showBoard() {
		System.out.println("The board currently");
		for (int i = 0; i < 9; i++) {
				System.out.print(board[i]);
			if (i == 2 || i == 5 || i == 8) {
				System.out.println("");
			}
		}
	}

	public String boardString() {
		String st = "";
		for (int i = 0; i < 9; i++) {
				if (board[i] == EMPTY) {
					st += ".";
				} else {
					st += board[i];
				}
		}
		return st;
	}

	int getTotalMoves() {
		return totalMoves;
	}

	private int check(int[] places) {
		int comp = board[places[0]];
		if (comp == EMPTY) {
			return EMPTY;
		}
		if (board[places[1]] == comp && board[places[2]] == comp) {
			return comp;
		} else {
			return EMPTY;
		}
	}
	/**
	 * returns -1 = no winner, player index for the winner
	 */
	public int checkForWin() {
		for (int i = 0; i < WIN_CHECKS.length; i++) {
			int result = check(WIN_CHECKS[i]);
			if (result != EMPTY) {
				return result;
			}
		}
		return EMPTY;
	}
		
	public boolean move(int whichPlayer, int x, int y) throws WrongTurnException {
		System.out.println("player " + whichPlayer + " moves x = " + x + " y = " + y);
		
		if (whichPlayer != toMove) throw new WrongTurnException();
		
		int index = x + 3*y;
		if (index < 0 || index > 8 || board[index] != EMPTY) {
			return false; 
		}	
		board[index] = whichPlayer;
		totalMoves++;
		nextMover();
		return true;
	}
}