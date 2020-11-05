package server;

import java.net.Socket;

import request.ArgMap;
import request.SimpleRequest;
import response.SimpleResponse;

public class SongServerRunnable extends ServerRunnable {

	public SongServerRunnable(Socket clientSocket, Socket dbSocket) {
		super(clientSocket, dbSocket);
	}

	@Override
	protected SimpleResponse doGET(SimpleRequest request) {
		// And this is why we have to fix the resource here!
		if (!request.getResource().equals("song")) {
			return new SimpleResponse(SimpleResponse.STATUS_NOTFOUND,"{}");
		}	
		// (0#song#mood:angry,artist:Pink) -->
		// get # {"mood: "angry", "artist": "Pink"}
		ArgMap arg = request.getArgMap();
		System.out.println("GET " + arg);

		String dbRequest = "get # " + arg.toJSON(); 
		System.out.println("DB Req: " + dbRequest);	
		String dbResponse = sendDBQuery(dbRequest);
		return new SimpleResponse(SimpleResponse.STATUS_OK,dbResponse);
	}

	@Override
	protected SimpleResponse doPOST(SimpleRequest request) {
		if (!request.getResource().equals("song")) {
			return new SimpleResponse(SimpleResponse.STATUS_NOTFOUND,"{}");
		}
		ArgMap arg = request.getArgMap();
		System.out.println("POST " + arg);
		// {"title":"Good Life","artist":"One Republic","mood":"happy"} -->
		//    insert : {"title":"Good Life","artist":"One Republic","mood":"happy"}
		String dbRequest = "insert # " + arg;
		System.out.println("DB Req: " + dbRequest);	
		String dbResponse = sendDBQuery(dbRequest);
		return new SimpleResponse(SimpleResponse.STATUS_OK,dbResponse);
	}

	@Override
	protected SimpleResponse doDELETE(SimpleRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
