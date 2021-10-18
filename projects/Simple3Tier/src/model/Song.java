package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Song {
	String INSERT_STRING = "INSERT INTO Songs VALUES (?, ?, ?)";
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((mood == null) ? 0 : mood.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (mood == null) {
			if (other.mood != null)
				return false;
		} else if (!mood.equals(other.mood))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

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
