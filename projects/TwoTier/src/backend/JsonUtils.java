package backend;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

class JsonUtils {
	
	static JsonObject fromString(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		return jsonReader.readObject();		
	}
}
