package dbserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Song;

public class DBServer {
	private static String QUERY_ALL = "SELECT * FROM Songs";
	private static String QUERY_ARTIST = "SELECT * FROM Songs WHERE UPPER(artist) LIKE ?";
	private static String QUERY_MOOD = "SELECT * FROM Songs WHERE UPPER(mood) LIKE ?";
	final static String DB_URL = "jdbc:mysql://localhost:3306/Songs?serverTimezone=UTC&user=root&password=mysql4me";
	private Connection conn;
	
	public static void init() throws ClassNotFoundException {
		// Load MySQL JDBC driver
		// Fully qualified driver name
		String driver = "com.mysql.cj.jdbc.Driver";
		// Trigger class loader
		Class.forName(driver);
	}

	public static Connection getConnection() throws SQLException {
//		String url = "jdbc:mysql://localhost:3306/Songs";
//		String username = "root";
//		String password = "mysql4me";
		return DriverManager.getConnection(DB_URL);
	}
	
	public DBServer() {
		try {
			init();
			conn = getConnection();
		} catch (Exception e) {
			conn = null;
		}
	}
	
	public DBServer(boolean confirm) {
		this();
		if (confirm) {
			try {
				PreparedStatement ps = conn.prepareStatement(QUERY_ALL);
				ResultSet rs = ps.executeQuery();
			    while ( rs.next() )
			    {
			      Song s = new Song(rs.getString("title"),rs.getString("artist"), rs.getString("mood"));
			      System.out.println(s);
			    }
			    rs.close();
			    ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Song> queryArtist (String artist) {
		List<Song> result = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_ARTIST);
			ps.setString(1,artist);
			ResultSet rs = ps.executeQuery();
		    while ( rs.next() )
		    {
		    	result.add(new Song(rs.getString("title"),rs.getString("artist"), rs.getString("mood")));
		    }
		    rs.close();
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Song> queryMood (String mood) {
		List<Song> result = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_MOOD);
			ps.setString(1,mood);
			ResultSet rs = ps.executeQuery();
		    while (rs.next())
		    {
		    	result.add(new Song(rs.getString("title"),rs.getString("artist"), rs.getString("mood")));
		    }
		    rs.close();
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
