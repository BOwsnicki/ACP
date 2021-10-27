package webserver;

/**
 * Code is taken from Computer Networking: A Top-Down Approach Featuring
 * the Internet, second edition, copyright 1996-2002 J.F Kurose and K.W. Ross,
 * All Rights Reserved.
 **/
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author bernd
 * A bit enhanced from the original version in
 * Kurose, Ross: Computer Networking - A Top-Down Approach Using the Internet
 *
 * To do: Wrap the inner request processing part into a thread to free the
 * server port listener for the next request
 *
 */
public class WebServerSingle {
    // Define the root directory for the resources
    // Change that for your setup
    private static final String serverRoot = "serverRoot/";
    
	// Also clear: The http port (make it 8888 so not to collide with a "real" server!)
    // Find port listeners on linux: sudo lsof -i -P -n | grep LISTEN
    private static final int port = 8888;

    // Need that to pump out status (error) codes
    private static DataOutputStream outToClient;

    // Logging stuff
    private static final String LOG_FILE = "logs/server.log";
    private static final Logger LOGGER = Logger.getLogger(WebServerSingle.class.getName());
    static {
        FileHandler fh = null;
		try {
			fh = new FileHandler(LOG_FILE);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        LogManager.getLogManager().reset();
        LOGGER.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
    }
    
    // This is a quick & dirty error handler
    // Writing the string into the log
    // Yes, I know it should send the response status field, I KNOW THAT!!!!
    // Send it back to the client with a message body it can display
    // errStr is something like "400 - Not found" or similar
    private static void error(String errStr) throws Exception {
    	LOGGER.log(Level.WARNING, errStr);
        outToClient.writeBytes("HTTP/1.0 " + errStr + "\r\n");
        outToClient.writeBytes("Content-Type: text.html\r\n");
        outToClient.writeBytes("Content-Length: " + errStr.length() + "\r\n");
        outToClient.writeBytes("\r\n" + errStr);
    }

    // This contains the main server loop
    // As stated above making it multi-threaded is the way to go
    // but for the time being that's fine...
    public static void main(String argv[]) throws Exception {
        // Set up a socket on the main port
        ServerSocket listenSocket = new ServerSocket(port);
        LOGGER.log(Level.INFO,"Listening at " + port);

        // Main loop: Wait for, read and process aq client request
        while (true) {
            // request received: the request "text" will come in over another socket
            Socket connectionSocket = listenSocket.accept();
            LOGGER.log(Level.INFO,"Request received");

            // Connections to the client are set up as Java streams - pretty convenient!
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            // Let's go to work: What's the request?
            // requestMessageLine = inFromClient.readLine();
            String inputLine;
        	StringBuffer content = new StringBuffer();
        	
        	// Close enough to catch the beginning of the request at least
        	while (!(inputLine = inFromClient.readLine()).equals("")) {
        	    content.append(inputLine);
        	}
        	String requestMessageLine = content.toString();

            // Now we have the request text - can print it into the log
        	LOGGER.log(Level.INFO,requestMessageLine);

            // Ok! What is it? We're not a really smart server - we'll just accept "GET" methods
            StringTokenizer tokenizedLine =
                    new StringTokenizer(requestMessageLine);

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
                    contentType = "Content-Type: text/html\r\n";
                }

                if (fileName.endsWith(".jpg")) {
                    contentType = "Content-Type: image/jpeg\r\n";
                }

                if (fileName.endsWith(".gif")) {
                    contentType = "Content-Type: image/gif\r\n";
                }

                if (contentType == null) { // not something we can serve here
                    error("415 - Unsupported Media Type");
                    continue;
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
                } catch (FileNotFoundException e) {
                    error("404 - Not found");
                }
                connectionSocket.close();
                listenSocket.close();
            } else {
                error("400 - Bad Request");
            }
        }
    }
}
