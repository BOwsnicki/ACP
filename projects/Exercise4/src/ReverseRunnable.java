import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ReverseRunnable implements Runnable {
	private Socket socket;
	
	public ReverseRunnable(Socket socket) {
		this.socket = socket;
	}
	
    public void run() {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            String request;
            while (((request = in.nextLine()) != null) && !request.equalsIgnoreCase("quit")) {
            	System.out.println("Message received:" + request);
                out.println(new StringBuilder(request).reverse().toString());
                out.flush();
            }
        } catch (IOException ex) {
        	System.err.println("Unable to get streams from client");
        } finally {
            try {
            	System.out.println("\"quit\" received. Terminating.");
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
