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

public class TextClient {

  public static void main(String args[]) {
      String host = "127.0.0.1";
      int port = 8889;
      new TextClient(host, port);
  }

  public TextClient(String host, int port) {
      try {
          String serverHostname = new String("127.0.0.1");

          System.out.println("Connecting to host " + serverHostname + " on port " + port + ".");
          System.out.println("Enter message, q to quit");
          Socket echoSocket = null;
          PrintWriter out = null;
          BufferedReader in = null;

          try {
              echoSocket = new Socket(serverHostname, 8889);
              out = new PrintWriter(echoSocket.getOutputStream(), true);
              in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
          } catch (UnknownHostException e) {
              System.out.println("Unknown host: " + serverHostname);
              System.exit(1);
          } catch (IOException e) {
              System.out.println("Unable to get streams from server");
              e.printStackTrace();
              System.exit(1);
          }

          /** {@link UnknownHost} object used to read from console */
          BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

          while (true) {
              System.out.print("client: ");
              String userInput = stdIn.readLine();
              /** Exit on 'q' char sent */
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
          echoSocket.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}

