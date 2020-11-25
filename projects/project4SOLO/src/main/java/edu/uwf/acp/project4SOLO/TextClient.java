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
    private String mySymbol;
    private String serverSymbol;

	private static String getCmd(String response) {
		String[] split = response.split(" ");
		return split[0];
	}
	
	private void showBoard(String board) {
		int count = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				char piece = board.charAt(count++);
				if (piece == '.') {
					System.out.print("|   |");					 
				} else {
					String symbol = mySymbol;
					if (piece == '0' + GameController.COMPUTER) {
						symbol = serverSymbol;
					}
					System.out.print("| " + symbol + " |");
				}
			}
			System.out.println();
		}
	}
	
  public static void main(String args[]) {
      String host = "127.0.0.1";
      int port = 5000;
      new TextClient(host, port);
  }

  public void sendRequest(String msg) {
  	  System.out.println("sending: " + msg);
	  out.println(msg);
	  out.flush();
  }
  
  private void requestBoard() throws IOException {
      sendRequest("board");
      String board = in.readLine();
      System.out.println("server: " + board);
      showBoard(board.split(" ")[1]);
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
      	  
      	  String response = in.readLine();
      	  System.out.println("server: " + response);
          mySymbol = response.split(" ")[1];
          serverSymbol = (mySymbol.equals("X") ? "O" : "X"); 
          System.out.println("symbols: " + mySymbol + " " + serverSymbol);
  		  boolean gameRunning = true;
      	  while (gameRunning) {
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
           	  requestBoard();
      		  System.out.print("Enter move: ");
              String userInput = stdIn.readLine();

              sendRequest("move " + userInput);
              System.out.println("server: " + in.readLine());
          }

          /** Closing all the resources */

       	  requestBoard();
      	  sendRequest("quit");
      	  System.out.println("server: " + in.readLine());
          out.close();
          in.close();
          stdIn.close();
          socket.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}

