package edu.uwf.cs.acp.P42021;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LingoServer {
	final static int TIC_PORT = 5000;

	public LingoServer() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(TIC_PORT);
			System.out.println("Tic Tac Toe Server Started.");

			while (true) {
				GameController controller = new GameController();
				
				Socket socket = server.accept();
				GameRunnable player = new GameRunnable(socket, controller);
				controller.setPlayer(player);
				new Thread(player).start();
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
		new LingoServer();
	}
}
