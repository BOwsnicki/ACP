package edu.uwf.acp.project4SOLO;

/*****************************************************************
Program source: https://www.admfactory.com/socket-example-in-java/
*****************************************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TextClient {
	private static final String STATUS_CMD = "status";
	private BufferedReader stdIn;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

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
      int port = 5000;
      new TextClient(host, port);
  }

  public void sendRequest(String msg) {
	  out.println(msg);
	  out.flush();
  }
  
  public TextClient(String host, int port) {
      try {
    	  stdIn = new BufferedReader(new InputStreamReader(System.in));
    	  System.out.println("Connecting to host " + host + " on port " + port + ".");
          socket = null;
          out = null;
          in = null;

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
      	  String command = "play";
      	  sendRequest(command);
      	  System.out.println("Sending: " + command);
      	  
      	  String response = in.readLine();
      	  System.out.println("server: " + response);
        	  
  		  boolean gameRunning = true;
      	  while (gameRunning) {
   			  System.out.println(STATUS_CMD);
   			  sendRequest(STATUS_CMD);
   			  response = in.readLine();
   			  System.out.println("server: " + response);
   			  switch (getCmd(response)) {
      			  case "draw":
      			  case "win": 	gameRunning = false;
      			  				break;
      			  default:		
   			  }
      		  if (!gameRunning) {
      			  break;
      		  }
           	  System.out.println("Enter move: ");
              String userInput = stdIn.readLine();
              // Exit on 'q' char sent 
              if ("q".equals(userInput)) {
                  break;
              }
              out.println("move " + userInput);
              System.out.println("server: " + in.readLine());
              System.out.println("Sending board request");
              sendRequest("board");
              System.out.println("server: " + in.readLine());
          }

          /** Closing all the resources */
      	  
      	  sendRequest("quit");
          out.close();
          in.close();
          stdIn.close();
          socket.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}

