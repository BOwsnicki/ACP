package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
                Thread t = new Thread(new ServerRunnable(socket,dbSocket));
                t.start();
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
