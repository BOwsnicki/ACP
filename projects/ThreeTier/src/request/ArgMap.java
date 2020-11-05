package request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ArgMap {
	private Map<String,String> assoc;

	public ArgMap() {
		super();
		assoc = new HashMap<>();
	}
	
	public void add(String key, String value) {
		assoc.put(key,value);
	}
	
	public String get(String key) {
		return assoc.get(key);
	}
	
	public String getAssoc(String key) {
		return key + ":" + get(key);
	}
	
	public Set<String> keys() {
		return assoc.keySet();
	}
	
	public String toJSON() {
		JsonObjectBuilder mapBuilder = Json.createObjectBuilder();
		for (String key : keys()) {
			mapBuilder.add(key, get(key));
		}
		return mapBuilder.build().toString();
	}
	
	@Override
	public String toString() {
		String result = "";
		boolean first = true;
		for (String key : keys()) {
			if (first) {
				first = false;
			} else {
				result += ",";
			}
			result += getAssoc(key);
		}
		return result;
	}
}
