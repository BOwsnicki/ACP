package server;

import java.net.Socket;

import request.SimpleRequest;
import response.SimpleResponse;

public class SongServerRunnable extends ServerRunnable {

	public SongServerRunnable(Socket clientSocket, Socket dbSocket) {
		super(clientSocket, dbSocket);
	}

	@Override
	protected SimpleResponse processGET(SimpleRequest request) {
		String arg = request.getArg();
		System.out.println("GET " + arg);
		// mood = angry --> get : mood = 'angry'
		String[] reqSplit = arg.toString().split(" ");
		String dbRequest = "get # " + reqSplit[0].trim() + " = '" + reqSplit[2].trim() + "'"; 
		System.out.println("DB Req: " + dbRequest);	
		String dbResponse = sendDBQuery(dbRequest);
		return new SimpleResponse(SimpleResponse.STATUS_OK,dbResponse);
	}

	@Override
	protected SimpleResponse processPOST(SimpleRequest request) {
		String arg = request.getArg();
		System.out.println("POST " + arg);
		// {"title":"Good Life","artist":"One Republic","mood":"happy"} -->
		//    insert : {"title":"Good Life","artist":"One Republic","mood":"happy"}
		String dbRequest = "insert # " + arg;
		System.out.println("DB Req: " + dbRequest);	
		String dbResponse = sendDBQuery(dbRequest);
		return new SimpleResponse(SimpleResponse.STATUS_OK,dbResponse);
	}

}
