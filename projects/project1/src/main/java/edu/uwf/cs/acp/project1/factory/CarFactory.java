package edu.uwf.cs.acp.project1.factory;

import edu.uwf.cs.acp.project1.classes.Car;

public class CarFactory {
	public static Car createRandomCar() {
		return new Car("Honda", "midsize", 800, 55);
	}
}
