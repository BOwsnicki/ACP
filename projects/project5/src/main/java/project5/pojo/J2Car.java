package project5.pojo;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

// package visible on purpose!
class J2Car {
	
	static Car fromJSONString(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		return new Car(object.getString("make"),object.getString("size"),object.getInt("weight"),object.getInt("horsepower"));
	}
	
	static String toJSONString(Car car) {
		JsonObjectBuilder carBuilder = Json.createObjectBuilder();
		carBuilder.add("make", car.getMake()).
					add("size", car.getSize()).
					add("weight", car.getWeight()).
					add("horsepower", car.getHorsePower());
		JsonObject carObject = carBuilder.build();
		return carObject.toString();
	}
	/*
	public static void main(String[] args) {
		Car c = new Car("Good Life","One Republic","happy");
		System.out.println(s);
		String j = "{\"title\":\"Good Life\",\"artist\":\"One Republic\",\"mood\":\"happy\"}";
		System.out.println(j);
		s = Song.fromString(j);
		System.out.println(s);
	}
	*/
}
