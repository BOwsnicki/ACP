package exercise4;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ReverseRunnable implements Runnable {
	
	public ReverseRunnable(Socket socket) {
		// Fill in
	}
	
    public void run() {
    	// set up IO instances
        try {
            String request;
            // Main request loop 
            // 	read until quit
            //	respond with reverse
            while (...) {
            	// Fill in
            }
        } catch (IOException ex) {
        	System.err.println("Unable to get streams from client");
        } finally {
            try {
            	// Close all resources
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
