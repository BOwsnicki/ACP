package banking.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program tests the bank server.
 */
public class BankClient {
	public static void main(String[] args) {
		final int SBAP_PORT = 8888;
		
		Socket s = null;
		InputStream instream = null;
		OutputStream outstream = null;
		Scanner in = null;
		PrintWriter out = null;
		
		Scanner con = new Scanner(System.in);
		try {
			s = new Socket("localhost", SBAP_PORT);
			instream = s.getInputStream();
			outstream = s.getOutputStream();
			in = new Scanner(instream);
			out = new PrintWriter(outstream);		
			System.out.print("Enter command like: DEPOSIT 3 1000 or quit to quit: ");
			String com = con.nextLine();
			while (!com.equals("quit")) {
				com += "\n";
				System.out.print("Sending: " + com);
				out.print(com);
				out.flush();
				String response = in.nextLine();
				System.out.println("Receiving: " + response);
				System.out.print("Enter command like: DEPOSIT 3 1000 or quit to quit: ");
				com = con.nextLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
				con.close();
				instream.close();
				outstream.close();
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}