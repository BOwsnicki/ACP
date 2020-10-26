package exercise4;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {

  public Client(String host, int port) {
      try {
    	  // Init and welcome message
          try {
        	  // Get a socket from the server
        	  // Create I/O instances from socket
        	  // Fill in
          } catch (UnknownHostException e) {
        	// Fill in
          } catch (IOException e) {
        	// Fill in
          }

          // Create input from System.in

          // Main client loop
          boolean done = false;
          while (!done) { 
        	  // Fill in
          }

          // Close all resources
          
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

