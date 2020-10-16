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
public class WebServerThreaded {
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
    private static final Logger LOGGER = Logger.getLogger(WebServerThreaded.class.getName());
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
    
    // This contains the main server loop
    // As stated above making it multi-threaded is the way to go
    // but for the time being that's fine...
    public static void main(String argv[]) throws Exception {
        // Set up a socket on the main port
        ServerSocket listenSocket = new ServerSocket(port);
        // LOGGER.log(Level.INFO,"Listening at " + port);
        System.err.println("Listening at " + port);
        
        // Main loop: Wait for, read and process aq client request
        while (true) {
            // request received: the request "text" will come in over another socket
            LOGGER.log(Level.INFO,"Request received");
            RequestHandler rh = new RequestHandler(listenSocket.accept(), LOGGER);
            rh.start();
        }
    }
}
