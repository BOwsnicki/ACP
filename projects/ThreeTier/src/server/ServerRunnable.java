package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import request.SimpleRequest;
import response.SimpleResponse;

public abstract class ServerRunnable implements Runnable {

	protected Socket clientSocket;
	protected Socket dbSocket;

	protected Scanner clientScanner;
	protected PrintWriter clientWriter;
	
	protected Scanner dbScanner;
	protected PrintWriter dbWriter;
	

	protected abstract SimpleResponse processGET(SimpleRequest req);

	protected abstract SimpleResponse processPOST(SimpleRequest req);

	public ServerRunnable(Socket clientSocket, Socket dbSocket) {
		super();
		this.clientSocket = clientSocket;
		this.dbSocket = dbSocket;
	}

	
	private void sendResponse(SimpleResponse resp) {
		clientWriter.println(resp);
		clientWriter.flush();
	}

	private SimpleResponse processRequest(String request) {
		SimpleRequest req = SimpleRequest.fromString(request);
		switch (req.getMethod()) {
		case SimpleRequest.GET:
			return processGET(req);
		case SimpleRequest.POST:
			return processPOST(req);
		default:
			return new SimpleResponse(SimpleResponse.STATUS_BAD_REQUEST, null);
		}
	}

	@Override
	public void run() {
		clientScanner = null;
		clientWriter = null;
		dbWriter = null;
		dbScanner = null;
		try {
			clientScanner = new Scanner(clientSocket.getInputStream());
			clientWriter = new PrintWriter(clientSocket.getOutputStream());
			dbScanner = new Scanner(dbSocket.getInputStream());
			dbWriter = new PrintWriter(dbSocket.getOutputStream());
			String request;
			while (((request = clientScanner.nextLine()) != null)) {
				System.out.println("Message received:" + request);
				SimpleResponse response = processRequest(request);
				System.out.println("Responding:" + response);
				sendResponse(response);
			}
		} catch (IOException ex) {
			System.err.println("Unable to get streams from client");
		} finally {
			try {
				clientScanner.close();
				clientWriter.close();
				clientSocket.close();
				dbScanner.close();
				dbWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
