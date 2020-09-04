package edu.uwf.cs.acp.project1.patterns;

import java.util.Random;

import edu.uwf.cs.acp.project1.classes.Car;

public class CarFactory {
	static final String[] makes = {"Chevy", "Ford", "Toyota", "Nissan", "Hyundai"};
	static final String[] sizes = {"compact", "intermediate", "fullSized"};
	
	private static String getRandomElement(String[] array) {
		int rnd = new Random().nextInt(array.length);
    	return array[rnd];
	}

	private static int getRandomInt(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static Car createRandomCar() {
		return new Car(getRandomElement(makes), getRandomElement(sizes), 
				getRandomInt(500,4000),getRandomInt(30,400)); 
	}
}
