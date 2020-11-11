package server;

import java.net.Socket;

import jsonutils.JsonUtils;
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
		if (!request.getResourcePath()[0].equals("song")) {
			return new SimpleResponse(SimpleResponse.STATUS_NOTFOUND,"{}");
		}	
		// (0#song#mood:angry,artist:Pink) -->
		// get # {"mood: "angry", "artist": "Pink"}
		ArgMap arg = request.getArgMap();
		System.out.println("GET " + arg);

		String dbRequest = "get # " + arg.toJSON(); 
		System.out.println("DB Req: " + dbRequest);	
		String dbResponse = sendDBQuery(dbRequest);
		if (request.getResourcePath().length == 2 && 
			request.getResourcePath()[1].equals("count")) {
				dbResponse = "{ \"count\": " + JsonUtils.lengthFromString(dbResponse) + "}";
		}
		return new SimpleResponse(SimpleResponse.STATUS_OK,dbResponse);
	}

	@Override
	protected SimpleResponse doPOST(SimpleRequest request) {
		if (!request.getResourcePath()[0].equals("song")) {
			return new SimpleResponse(SimpleResponse.STATUS_NOTFOUND,"{}");
		}
		if (request.getResourcePath().length > 1) {
			return new SimpleResponse(SimpleResponse.STATUS_BAD_REQUEST,"{}");
		}
	
		// (1#song#mood:angry,artist:Body Count,title:Institutionalized) -->
		// insert # {"title":"Institutionalized", "artist":"Body Count", "mood":"angry"}
		ArgMap arg = request.getArgMap();
		System.out.println("POST " + arg);
		String dbRequest = "insert # " + arg.toJSON();
		System.out.println("DB Req: " + dbRequest);	
		String dbResponse = sendDBQuery(dbRequest);
		return new SimpleResponse(SimpleResponse.STATUS_OK,dbResponse);
	}

	@Override
	protected SimpleResponse doDELETE(SimpleRequest request) {
		if (!request.getResourcePath()[0].equals("song")) {
			return new SimpleResponse(SimpleResponse.STATUS_NOTFOUND,"{}");
		}
		if (request.getResourcePath().length > 1) {
			return new SimpleResponse(SimpleResponse.STATUS_BAD_REQUEST,"{}");
		}
		// (2#song#mood:happy) -->
		// delete # {"mood: "happy"}
		ArgMap arg = request.getArgMap();
		System.out.println("DELETE " + arg);

		String dbRequest = "delete # " + arg.toJSON(); 
		System.out.println("DB Req: " + dbRequest);	
		String dbResponse = sendDBQuery(dbRequest);
		return new SimpleResponse(SimpleResponse.STATUS_OK,dbResponse);
	}
}
