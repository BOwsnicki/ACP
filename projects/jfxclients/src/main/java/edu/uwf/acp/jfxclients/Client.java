package edu.uwf.acp.jfxclients;

/*****************************************************************
Program source: https://www.admfactory.com/socket-example-in-java/
*****************************************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 8081;

	private PrintWriter out = null;
	private BufferedReader in = null;
	private Socket echoSocket = null;

	public static void main(String args[]) {
		new Client();
	}

	public Client() {
		try {
			try {
				echoSocket = new Socket(HOST, PORT);
				out = new PrintWriter(echoSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			} catch (UnknownHostException e) {
				System.out.println("Unknown host: " + HOST);
				System.exit(1);
			} catch (IOException e) {
				System.out.println("Unable to get streams from server");
				e.printStackTrace();
				System.exit(1);
			}
		} finally {
		}
	}

	public String sendString(String s) {
		try {
			out.println(s);
			return in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		/** Closing all the resources */
		try {
			out.close();
			in.close();
			echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
