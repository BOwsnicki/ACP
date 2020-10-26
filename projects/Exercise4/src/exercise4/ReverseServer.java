package exercise4;

import java.io.IOException;
import java.net.ServerSocket;

public class ReverseServer  {    
    private static final int PORT_NUMBER = 8765;

    public static void main(String[] args) {
        System.out.println("EchoServer running");

        ServerSocket server = null;
        try {
            // Obtain server socket
            while (true) {
            	// Wait for connection
            	// Create new Runnable
            	// Pass the new Socket
            	// Start the Thread
            }
        } catch (IOException ex) {
            System.err.println("Unable to start server.");
        } finally {
        	// Close everything
        }
    }
}