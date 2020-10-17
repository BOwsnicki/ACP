package edu.uwf.acp.ttt;


import java.net.*;
import java.io.*;

// set up a TicTacToeServer for two client applets

public class TicTacToeServer {
   private byte board[];
   private Player players[];
   private ServerSocket server;
   private int numberOfPlayers;
   private int currentPlayer;

   public TicTacToeServer() {
	board = new byte[9];
	players = new Player[2];
	currentPlayer = 0;
	
	// set up ServerSocket
	try
	{	server = new ServerSocket (5000);
		System.err.println("Listening on port 5000");
	}
	catch ( IOException e)
	{	e.printStackTrace();
		System.exit(1);				}
   }
 
   public void execute()
	// wait for two connections so game can be played
   {
	
	for ( int i = 0; i < players.length; i++ ) {
		try
		{	
		players[i] = new Player( server.accept(), this, i );
		players[i].start();
		++numberOfPlayers;
		}
		catch (IOException e)
		{	e.printStackTrace(); System.exit(1);	}
	}
   }

   public int getNumberOfPlayers()	{	return numberOfPlayers;	}

   public synchronized boolean validMove (int loc, int player)
 	// Determine if move is valid. This method is synchronized so that only 
	// one move can be made at a time
   {   
	   System.err.println("Checking " + loc + " for player " + player);
	while ( player != currentPlayer ) {
		try 
		{	wait();	}
		catch	(InterruptedException e)
		{		}
	}

	if ( !isOccupied (loc) )
	{
		board[loc] = (byte) (currentPlayer == 0 ? 'X' : 'O' );
		currentPlayer = ++currentPlayer % 2;
		players[currentPlayer].otherPlayerMoved(loc);
		notify();		// tell waiting player to continue
		return true;
	}
	else
		return false;
   }

   public void display(String s) {
	   System.out.println(s);
   }
   
   public boolean isOccupied (int loc)
   {
	if ( board[loc] == 'X' || board[loc] == 'O' )
		return true;
	else
		return false;
   }

   public boolean gameOver()
   {
	return false;
   }

   public static void main( String args[] )  {
	TicTacToeServer game = new TicTacToeServer();
	game.execute();
   }
}

class Player extends Thread {
// Player class to manage each Player as a thread
	Socket connection;
	// DataInputStream input;
	BufferedReader input;
	DataOutputStream output;
	TicTacToeServer control;
	int number;
	char mark;
    
  public Player (Socket sock, TicTacToeServer tttServer, int num )
  {
	mark = ( num == 0 ? 'X' : 'O' );
	connection = sock;

	try
	{
	    input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		// input = new DataInputStream(connection.getInputStream() );
		output = new DataOutputStream(connection.getOutputStream() );
	}
	catch (IOException e)
		{	e.printStackTrace(); System.exit(1);	}

	control = tttServer;
	number = num;
   }

   public void otherPlayerMoved (int loc )
   {
	try {
		output.writeUTF( "Opponent moved" );
		output.writeInt( loc );
	}
	catch (IOException e)	{}
   }

   private static void writeString(DataOutputStream dos, String s) throws IOException {
	   dos.write(s.getBytes());
   }
   
   public void run()
   {
	boolean done = false;

	try {
 		System.err.println( "Player " + ( number == 0 ? 'X' : 'O' ) + " connected" );
		writeString(output, "Player " + ( number == 0 ? "X connected " : 
					"O connected, please wait\n "));
	// wait for another player to arrive
		if ( control.getNumberOfPlayers() < 2 )
		{
			writeString(output, "Waiting for another player\n" );
			while (control.getNumberOfPlayers() < 2) {
				Thread.sleep(1000);
		}
			writeString(output, "Other player connected.\n");
		} else {
			// writeString(output, "Both players connected. Please wait.\n");
		}
		output.flush();
	// play game
		while ( !done )
		{
			writeString(output, "Your move.\n");
			String response = input.readLine();
			System.err.println(number + " " + response);
			int location = Integer.parseInt(response);
			// int location = 3;
			if (control.validMove (location, number ) )
			{
				control.display ( "loc: " + location );
				writeString(output, "Valid move" );
			}
			else
				writeString(output, "Invalid move, try again!!!" );

			if (control.gameOver() )
				done = true;
		}
		connection.close();
	}
	catch (IOException e)
		{	e.printStackTrace(); System.exit(1);	} 
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}	

