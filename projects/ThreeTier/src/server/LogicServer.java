package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LogicServer {
	private static final int SERVER_PORT = 7878;
	private static final int DB_PORT = 7070;
	private static final String DB_HOST = "127.0.0.1";
	
    public static void main(String[] args) {
        ServerSocket server = null;        
        try {
        	Socket dbSocket = new Socket(DB_HOST, DB_PORT);
            // Obtain server socket
            server = new ServerSocket(SERVER_PORT);
            System.out.println("Server started on port " + SERVER_PORT);
            while (true) {
                Socket socket = server.accept();
                // This is issue #1
                // The server thread is only able to work with "song resources
                // We should really get the request, check the resource
                //         and then select the correct server thread type
                // Always something more to do...
                new Thread(new SongServerRunnable(socket,dbSocket)).start();
            }
        } catch (IOException ex) {
            System.err.println("Unable to start server.");
        } finally {
            try {
              if (server != null)
                  server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }
}
