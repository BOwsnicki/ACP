package rental;

import java.lang.reflect.*;
import java.util.Arrays;

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