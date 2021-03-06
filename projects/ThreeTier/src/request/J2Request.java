package request;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

// {"method":0,"arg":"mood = angry"}
// {"method":1,"arg":"(Good Life,One Republic,happy)"}

// package visible on purpose!
class J2Request {
	
	static SimpleRequest fromJSONString(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		return new SimpleRequest(object.getInt("method"),object.getString("arg"));
	}
	
	static String toJSONString(SimpleRequest req) {
		JsonObjectBuilder songBuilder = Json.createObjectBuilder();
		songBuilder.add("method", req.getMethod()).
					add("arg", req.getArg());
		JsonObject reqObject = songBuilder.build();
		return reqObject.toString();
	}
}
