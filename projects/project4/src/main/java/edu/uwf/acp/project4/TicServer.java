package edu.uwf.acp.project4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TicServer {

	GameController game = new GameController();
	int number = 1;
	final static int TIC_PORT = 8889;

	public TicServer() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(TIC_PORT);
			System.out.println("Tic Tac Toe Server Started.");

			while (true) {
				Socket s = server.accept();
				System.out.println("Client connected.");
				GameRunnable action = new GameRunnable(s, game, number++);
				game.addPlayer(action);
				if (number > 2) {
					game = new GameController();
					number = 1;
				}
				Thread t = new Thread(action);
				t.start();
			}
		} catch (Exception e) {
		} finally {
			try {
				server.close();
			} catch (Exception e) {}
		}
	}

	public static void main(String[] args) throws IOException {
		new TicServer();
	}
}
