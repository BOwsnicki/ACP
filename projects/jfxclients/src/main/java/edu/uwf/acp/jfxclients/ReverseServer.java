package edu.uwf.acp.jfxclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ReverseServer extends Thread {
    private static final int PORT_NUMBER = 8081;
    private static final String LOG_FILE = "/home/cop4856/logs/example1.log";
    private static final Logger LOGGER = Logger.getLogger(ReverseServer.class.getName());
    static {
        FileHandler fh = null;
		try {
			fh = new FileHandler(LOG_FILE);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        LogManager.getLogManager().reset();
        LOGGER.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
    }
    
    protected Socket socket;

    private ReverseServer(Socket socket) {
        this.socket = socket;
        LOGGER.log(Level.INFO, "New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String request;
            while ((request = br.readLine()) != null) {
            	LOGGER.log(Level.INFO, "Message received:" + request);
                request += '\n';
                String reversed = new StringBuilder(request).reverse().toString();
                out.write(reversed.getBytes());
            }

        } catch (IOException ex) {
        	LOGGER.log(Level.SEVERE, "Unable to get streams from client");
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                new ReverseServer(server.accept());
            }
        } catch (IOException ex) {
            System.err.println("Unable to start server.");
            ex.printStackTrace();
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