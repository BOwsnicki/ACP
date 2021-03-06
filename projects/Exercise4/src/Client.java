
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

  public Client(String host, int port) {
      try {
          System.out.println("Connecting to host " + host + " on port " + port + ".");
          System.out.println("Enter message, q to quit");
          Socket echoSocket = null;
          PrintWriter out = null;
          BufferedReader in = null;

          try {
              echoSocket = new Socket(host, port);
              out = new PrintWriter(echoSocket.getOutputStream(), true);
              in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
          } catch (UnknownHostException e) {
              System.out.println("Unknown host: " + host);
              System.exit(1);
          } catch (IOException e) {
              System.out.println("Unable to get streams from server");
              e.printStackTrace();
              System.exit(1);
          }

          /** {@link UnknownHost} object used to read from console */
          BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

          boolean done = false;
          while (!done) {
              System.out.print("client: ");
              String userInput = stdIn.readLine();
              out.println(userInput);
              if ("quit".equals(userInput)) {
                  done = true;
              } else {
                  System.out.println("server: " + in.readLine());            	  
              }
          }

          /** Closing all the resources */
          out.close();
          in.close();
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

