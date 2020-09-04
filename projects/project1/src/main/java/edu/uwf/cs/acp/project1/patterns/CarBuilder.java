package edu.uwf.cs.acp.project1.patterns;

import edu.uwf.cs.acp.project1.classes.Car;

public class CarBuilder {
	private Car object;

	public CarBuilder newCar() {
		object = new Car();
		return this;
	}

	public CarBuilder addValue(String fieldName, Object value) {
		switch (fieldName.toLowerCase()) {
		case "horsepower":
			object.setHorsePower((Integer) value);
			break;
		case "size":
			object.setSize(((String) value).trim());
			break;
		case "weight":
			object.setWeight((Integer) value);
			break;
		case "make":
			object.setMake(((String) value).trim());
		default:
		}
		return this;
	}

	public Car build() {
		return object;
	}
}
