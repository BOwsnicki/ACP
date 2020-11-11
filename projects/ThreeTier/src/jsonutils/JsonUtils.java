package jsonutils;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonUtils {
	
	public static JsonObject objFromString(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		return jsonReader.readObject();		
	}
	
	public static JsonArray arrFromString(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		return jsonReader.readArray();		
	}
	
	public static int lengthFromString (String jsonString) {
		return arrFromString(jsonString).size();
	}
}
