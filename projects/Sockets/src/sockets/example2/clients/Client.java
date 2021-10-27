package sockets.example2.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

  public Client(String host, int port) {
      try {
    	  // Init and welcome message
    	  System.out.println("Connecting to host " + host + " on port " + port);
    	  System.out.println("Enter a message ('quit' to exit)");
    	  Socket echoSocket = null;
    	  PrintWriter out = null;
    	  BufferedReader in = null;
          try {
        	  // Get a socket from the server
        	  // Create I/O instances from socket
        	  echoSocket = new Socket(host, port);
        	  out = new PrintWriter(echoSocket.getOutputStream(), true);
        	  in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
          } catch (UnknownHostException e) {
        	  e.printStackTrace();
        	  System.out.println("Client unknownhost exception: " + host);
        	  System.exit(1);
          } catch (IOException e) {
        	  e.printStackTrace();
        	  System.out.println("Client I/O exception");
          }

          // Create input from System.in
          BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
          
          // Main client loop
          boolean done = false;
          while (!done) { 
        	  System.out.print("Client: ");
        	  String userInput = stdIn.readLine();
        	  out.println(userInput);
        	  out.flush();
        	  if (("quit").equals(userInput))
        	  {
        		  done = true;
        	  } else
        	  {
        		  System.out.println("Server: " + in.readLine());
        	  }
          }

          // Close all resources
          in.close();
          out.close();
          stdIn.close();
          echoSocket.close();
          
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void main(String args[]) {
      String host = "127.0.0.1";
      int port = 8765;
      new Client(host,port);
  }
}

