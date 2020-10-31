package response;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

// package visible on purpose!
class J2Response {
	
	static SimpleResponse fromJSONString(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		return new SimpleResponse(object.getInt("status"),object.getString("body"));
	}
	
	static String toJSONString(SimpleResponse resp) {
		JsonObjectBuilder songBuilder = Json.createObjectBuilder();
		songBuilder.add("status", resp.getStatus()).
					add("body", resp.getBody());
		JsonObject reqObject = songBuilder.build();
		return reqObject.toString();
	}
}
