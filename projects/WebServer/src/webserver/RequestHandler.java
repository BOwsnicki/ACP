package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler extends Thread {
    // Define the root directory for the resources
    // Change that for your setup
    private static final String serverRoot = "serverRoot/";
	
    private static int number = 0;
	private Socket connectionSocket;
	private Logger logger;
	private DataOutputStream outToClient;
	
	public RequestHandler(Socket connectionSocket, Logger logger) {
		super();
		number++;
		this.connectionSocket = connectionSocket;
		this.logger = logger;
		this.outToClient = null;
	}

    // This is a quick & dirty error handler
    // Writing the string into the log
    // Yes, I know it should send the response status field, I KNOW THAT!!!!
    // Send it back to the client with a message body it can display
    // errStr is something like "400 - Not found" or similar
    private void error(String errStr)  {
    	logger.log(Level.WARNING, "Handler-" + number + ": " + errStr);
    	try {
    		outToClient.writeBytes("HTTP/1.0 " + errStr + "\r\n");
    		outToClient.writeBytes("Content-Type: text.html\r\n");
    		outToClient.writeBytes("Content-Length: " + errStr.length() + "\r\n");
    		outToClient.writeBytes("\r\n" + errStr);
    	} catch (IOException e) {
			logger.log(Level.SEVERE, "Handler-" + number + ": " + e.getMessage());
    	}
    }
    
	@Override
	public void run() {
		StringBuffer content = new StringBuffer();
		try { // get request
			// Connections to the client are set up as Java streams - pretty convenient!
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			// Let's go to work: What's the request?
			// requestMessageLine = inFromClient.readLine();
			String inputLine;

			// Close enough to catch the beginning of the request at least
			while (!(inputLine = inFromClient.readLine()).equals("")) {
				content.append(inputLine);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Handler-" + number + ": " + e.getMessage());
		}
		String requestMessageLine = content.toString();

		// Now we have the request text - can print it into the log
		logger.log(Level.INFO, "Handler-" + number + ": " + requestMessageLine);

		// Ok! What is it? We're not a really smart server - we'll just accept "GET"
		// methods
		StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);
		
		String fileName;
		
		if (tokenizedLine.nextToken().equals("GET")) {
			// It's a get - good!
			// Extract the resource path and name
			fileName = tokenizedLine.nextToken();

			if (fileName.startsWith("/") == true) {
				fileName = fileName.substring(1);
			}
			// physical location is the given path/name relative to
			// the location of the server root directory
			fileName = serverRoot + fileName;

			// Check if we can serve these file types
			// Allowed html, htm, jpg and gif
			// For everything else, there's status 415
			// Submitted to the 2010 contest for the ugliest Java hack!
			String contentType = null;

			if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
				contentType = "Content-Type: text.html\r\n";
			}

			if (fileName.endsWith(".jpg")) {
				contentType = "Content-Type: image/jpeg\r\n";
			}

			if (fileName.endsWith(".gif")) {
				contentType = "Content-Type: image/gif\r\n";
			}

			if (contentType == null) { // not something we can serve here
				error("415 - Unsupported Media Type");
			}

			// Let's boldly read this thing
			// This can throw a FileNotFoundException which leads to a 404 status
			try {
				File file = new File(fileName);

				// Hoping we found it, read it into a local byte[]
				int numOfBytes = (int) file.length();
				byte[] fileInBytes = new byte[numOfBytes];
				FileInputStream inFile = new FileInputStream(fileName);
				inFile.read(fileInBytes);
				inFile.close();

				// Now the response!
				outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");
				outToClient.writeBytes(contentType);
				int realLength = numOfBytes;
				outToClient.writeBytes("Content-Length: " + realLength + "\r\n");
				outToClient.writeBytes("\r\n");
				outToClient.write(fileInBytes, 0, numOfBytes);
				connectionSocket.close();
			} catch (FileNotFoundException e) {
				error("404 - Not found");
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Handler-" + number + ": " + e.getMessage());
			}
		}
	}
}
