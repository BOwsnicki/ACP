package edu.uwf.acp.project4;

/*****************************************************************
Program source: https://www.admfactory.com/socket-example-in-java/
*****************************************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TextClient {
	private static final String STATUS_CMD = "status";
	
	private int myNumber;
	
	private static int getInt(String response, int index) {
		String[] split = response.split(" ");
		return Integer.parseInt(split[index]);
	}

	private static String getCmd(String response) {
		String[] split = response.split(" ");
		return split[0];
	}
	
	private static int[][] getBoard(String board) {
		int[][] intBoard = new int[3][3];
		int count = -1;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				switch (board.charAt(count++)) {
				case '.': intBoard[row][col] = -1; break;
				case '0': intBoard[row][col] = 0; break;
				case '1': intBoard[row][col] = 1; 
				}
			}
		}
		return intBoard;
	}
	
	private static void showBoard(String board) {
		int[][] intBoard = getBoard(board);
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int piece = intBoard[row][col];
				if (piece == -1) {
					System.out.print(" ");					 
				} else {
					System.out.print(piece);
				}
			}
		}
	}
	
  public static void main(String args[]) {
      String host = "127.0.0.1";
      Scanner in = new Scanner(System.in);
      int port = 5000;
      System.out.print("ID: ");
      String id = in.nextLine();
      // in.close();
      new TextClient(host, port, id);
  }

  public TextClient(String host, int port, String id) {
      try {
    	  BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    	  System.out.println("Connecting to host " + host + " on port " + port + ".");
          Socket socket = null;
          PrintWriter out = null;
          BufferedReader in = null;

          try {
              socket = new Socket(host, port);
              out = new PrintWriter(socket.getOutputStream(), true);
              in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          } catch (UnknownHostException e) {
              System.out.println("Unknown host: " + host);
              System.exit(1);
          } catch (IOException e) {
              System.out.println("Unable to get streams from server");
              e.printStackTrace();
              System.exit(1);
          }

          // Join game
      	  String login = "join " + id;
      	  out.println(login);
      	  System.out.println("Sending: "+login);
      	  
      	  String response = in.readLine();
      	  System.out.println("server: " + response);
        	  
      	  // Determine who I am (0 or 1)
      	  myNumber = getInt(response,2);
      	  System.out.println("index: " + myNumber);

      	  while (true) {
      		  // Wait until if it's my move
      		  boolean myMove = false;
      		  boolean gameEnded = false;
      		  while (!gameEnded && !myMove) {
      			  Thread.sleep(500);
      	      	  // This might hang until the second player joins!
      			  System.out.println(STATUS_CMD);
      			  out.println(STATUS_CMD);
      			  response = in.readLine();
      			  System.out.println("server: " + response);
      			  switch (getCmd(response)) {
      			  case "draw":
      			  case "win": 	gameEnded = true;
      			  				break;
      			  default:		int toMove = getInt(response,1);
      				  			System.out.println(toMove);
      				  			myMove = (myNumber == toMove);
      			  }
      		  }
      		  
           	  System.out.println("Enter message, q to quit");
           	  // System.out.println("server: " + in.readLine());
              System.out.print("client-" + id + ": ");
              String userInput = stdIn.readLine();
              // Exit on 'q' char sent 
              if ("q".equals(userInput)) {
                  break;
              }
              out.println(userInput);
              System.out.println("server: " + in.readLine());
          }

          /** Closing all the resources */
          out.close();
          in.close();
          stdIn.close();
          socket.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}

