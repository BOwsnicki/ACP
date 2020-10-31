package server;

import java.net.Socket;

import request.SimpleRequest;
import response.SimpleResponse;

public class SongServerRunnable extends ServerRunnable {

	public SongServerRunnable(Socket clientSocket, Socket dbSocket) {
		super(clientSocket, dbSocket);
	}

	@Override
	protected SimpleResponse processGET(SimpleRequest req) {
		String arg = req.getArg();
		System.out.println("GET " + arg);
		
		return null;
	}

	@Override
	protected SimpleResponse processPOST(SimpleRequest req) {
		String arg = req.getArg();
		System.out.println("POST " + arg);
		return null;
	}

}
