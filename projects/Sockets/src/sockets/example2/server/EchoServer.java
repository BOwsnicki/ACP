package sockets.example2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {    
    private static final int PORT_NUMBER = 8765;

    public static void main(String[] args) {
        System.out.println("EchoServer running");

        ServerSocket server = null;
        try {
            // Obtain server socket
        	server = new ServerSocket(PORT_NUMBER);
            while (true) {
            	Socket socket = server.accept();
            	Thread t = new Thread(new EchoRunnable(socket));
            	t.start();
            }
        } catch (IOException ex) {
            System.err.println("Unable to start server.");
        } finally {
        	try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}