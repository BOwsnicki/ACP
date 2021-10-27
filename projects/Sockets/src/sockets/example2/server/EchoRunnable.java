package sockets.example2.server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoRunnable implements Runnable {
	private Socket socket;
	public EchoRunnable(Socket socket) {
		this.socket = socket;
	}
	
    public void run() {
    	// set up IO instances
    	Scanner in = null;
    	PrintWriter out = null;
        try {
            String request;
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            // Main request loop 
            while (((request = in.nextLine()) != null) && !request.equalsIgnoreCase("quit")) {
            	System.out.println("Message received: " + request);
            	out.println(new String(request));
            	out.flush();
            }
        } catch (IOException ex) {
        	System.err.println("Unable to get streams from client");
        } finally {
            try {
            	// Close all resources
            	in.close();
            	out.close();
            	socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
