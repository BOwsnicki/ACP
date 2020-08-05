package rentalreflect;

import java.lang.reflect.*;

import rental.Car;

public class CarReflection {

	public void reflectDemos() {
		Method[] methods = Car.class.getMethods();

		for (Method method : methods) {
			System.out.println("method = " + method.getName());
		}
	}

	public static void main(String[] args) {
		CarReflection r1 = new CarReflection();
		r1.reflectDemos();
	}
}