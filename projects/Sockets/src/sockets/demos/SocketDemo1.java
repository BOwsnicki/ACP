package sockets.demos;

import java.net.Socket;

public class SocketDemo1 {
	public static void main(String[] args) {
		Socket s = new Socket();
		System.out.println(s.getClass());		
	}
}
