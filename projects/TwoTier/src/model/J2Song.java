package model;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

// package visible on purpose!
class J2Song {
	
	static Song fromJSONString(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		return new Song(object.getString("title"),object.getString("artist"),object.getString("mood"));
	}
	
	static String toJSONString(Song song) {
		JsonObjectBuilder songBuilder = Json.createObjectBuilder();
		songBuilder.add("title", song.getTitle()).
					add("artist", song.getArtist()).
					add("mood", song.getMood());
		JsonObject songObject = songBuilder.build();
		return songObject.toString();
	}
}
