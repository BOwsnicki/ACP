package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Song {
	String INSERT_STRING = "INSERT INTO Songs VALUES (?, ?, ?)";
	
	private String title;
	private String artist;
	private String mood;
	
	// Factory method!
	public static Song fromString(String jsonString) {
		return J2Song.fromJSONString(jsonString);
	}
	
	// For decoration atm
	public int persist(Connection c) throws SQLException {
		PreparedStatement p = c.prepareStatement("INSERT INTO Songs VALUES (?, ?, ?)");
		p.setString(1,title);
		p.setString(2,artist);
		p.setString(3,mood);
		return p.executeUpdate();
	}
	
	public Song(String title, String artist, String mood) {
		super();
		this.title = title;
		this.artist = artist;
		this.mood = mood;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}

	@Override
	public String toString() {
		return J2Song.toJSONString(this);
	}
}
