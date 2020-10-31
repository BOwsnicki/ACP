package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DBClient {
	private static final int DB_PORT = 7070;
  
	public DBClient(String host) {
      try {
          System.out.println("Connecting to host " + host + " on port " + DB_PORT + ".");
          System.out.println("Enter message, q to quit");
          Socket dbSocket = null;
          PrintWriter out = null;
          BufferedReader in = null;
          
          try {
        	  // Get a socket from the server
        	  // Create I/O instances from socket
        	  // Fill in
              dbSocket = new Socket(host, DB_PORT);
              out = new PrintWriter(dbSocket.getOutputStream(), true);
              in = new BufferedReader(new InputStreamReader(dbSocket.getInputStream()));
          } catch (UnknownHostException e) {
              System.out.println("Unknown host: " + host);
              System.exit(1);
          } catch (IOException e) {
              System.out.println("Unable to get streams from server");
              e.printStackTrace();
              System.exit(1);
          }

          // Create input from System.in
          BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
          
          // Main client loop
          boolean done = false;
          while (!done) { 
              System.out.print("client: ");
              String userInput = stdIn.readLine();
              out.println(userInput);
              out.flush();
              if ("quit".equals(userInput)) {
                  done = true;
              } else {
                  System.out.println("server: " + in.readLine());            	  
              }
          }

          // Close all resources
          out.close();
          in.close();
          stdIn.close();
          dbSocket.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void main(String args[]) {
      String host = "127.0.0.1";
      new DBClient(host);
  }
}

