package edu.uwf.acp.project4SOLO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicServer2 {
	final static int TIC_PORT = 5000;

	public TicServer2() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(TIC_PORT);
			System.out.println("Tic Tac Toe Server Started.");

			while (true) {
				GameController controller = new GameController();
				
				Socket s1 = server.accept();
				GameRunnable player = new GameRunnable(s1, controller);
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
		new TicServer2();
	}
}
