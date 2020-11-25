package edu.uwf.acp.project4SOLO;

public class GameController {
	public static final int EMPTY = -1;
	public static final int COMPUTER = 0;
	public static final int OTHER = 1;
	
	private static final int[][] WIN_CHECKS = 
		{
			{0,1,2}, {3,4,5}, {6,7,8},
			{0,3,6}, {1,4,7}, {2,5,8},
			{0,4,8}, {2,4,6}
	};
	
	public static final int STATE_RUNNING = 0;
	public static final int STATE_DRAW = 1;
	public static final int STATE_WINNING = 2;
	public int gameState;
	public int winningIndex;
	
	private int[] board = new int[9];
	private int totalMoves = 0;

	public GameController() {
		gameState = STATE_RUNNING;
		for (int i = 0; i < 9; i++)
				board[i] = EMPTY;
	}

	public void setPlayer(GameRunnable gr) {
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
	
	public void updateState() {
		System.out.println("checking for win ...");
		winningIndex = checkForWin();
		System.out.println("Win check: " + winningIndex);
		if (winningIndex != EMPTY) {
			gameState = STATE_WINNING;
		} else { System.out.println("*** " + totalMoves);
			if (totalMoves < 9) {
				System.out.println("Game continues");
				gameState = STATE_RUNNING;
			} else {
				System.out.println("Draw");
				gameState = STATE_DRAW;
			}
		}
		System.out.println("State " + gameState);
	}

	
	public boolean moveOther(int index) {
		System.out.println("player moves: " + index);
		if (index < 0 || index > 8 || board[index] != EMPTY) {
			return false; 
		}	
		board[index] = OTHER;
		totalMoves++;
		updateState();
		return true;
	}
	
	// That's the AI!!!
	// Create a legal move for the server
	// Here: First free square! 
	public int moveServer() {
		for (int i = 0; i < 9; i++) {
			if (board[i] == EMPTY) {
				board[i] = COMPUTER;
				totalMoves++;
				updateState();
				return i;
			}
		}
		return EMPTY;
	}
}