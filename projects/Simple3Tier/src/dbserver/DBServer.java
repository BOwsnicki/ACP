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
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
	}

	public static Connection getConnection() throws SQLException {
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
		return query(QUERY_ARTIST,artist);
	}
	
	public List<Song> queryMood (String mood) {
		return query(QUERY_MOOD,mood);
	}

	public List<Song> query (String query, String arg) {
		List<Song> result = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,arg);
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
