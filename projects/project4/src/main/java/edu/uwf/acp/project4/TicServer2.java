package edu.uwf.acp.project4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TicServer2 {
	final static int TIC_PORT = 5000;

	// Logging stuff
	private static final String LOG_FILE = "logs/ticserver.log";
	private static final Logger LOGGER = Logger.getLogger(TicServer2.class.getName());
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

	private static String joining(Socket socket, int number) throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String request;
		String[] command = null;
		if ((request = in.readLine()) != null) {
			LOGGER.log(Level.INFO, "Message received:" + request);
			command = request.split(" ");
			if (!command[0].contentEquals("join")) {
				out.write("Illegal command");
				return null;
			}
			if (number == 1) {
				out.println("joined " + command[1] + " 0");
			} else {
				out.println("joined " + command[1] + " 1");
			}
		}
		// in.close();
		// out.close();
		return command[1];
	}

	public TicServer2() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(TIC_PORT);
			System.out.println("Tic Tac Toe Server Started.");

			while (true) {
				GameController controller = new GameController(LOGGER);
				
				String name1 = null;
				Socket s1 = server.accept();
				while ((name1 = joining(s1,1)) == null) {
					s1 = server.accept();
				}
				LOGGER.log(Level.INFO, "Client 0 connected");
				GameRunnable action1 = new GameRunnable(s1, controller, 0);
				controller.addPlayer(action1);

				LOGGER.log(Level.INFO, "Waiting for Client 1");

				String name2 = null;
				Socket s2 = server.accept();
				while ((name2 = joining(s2,2)) == null) {
					s2 = server.accept();
				}
				LOGGER.log(Level.INFO, "Client 1 connected");
				GameRunnable action2 = new GameRunnable(s2, controller, 1);
				controller.addPlayer(action2);

				new Thread(action1).start();
				new Thread(action2).start();
			}
		} catch (Exception e) {
		} finally {
			try {
				server.close();
			} catch (Exception e) {
			}
		}
	}	

	public static void main(String[] args) throws IOException {
		new TicServer2();
	}
}
