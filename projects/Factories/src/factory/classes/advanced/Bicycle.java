package factory.classes.advanced;

public class Bicycle extends Vehicle {
	static {
		register(Bicycle.class);
		maxSpeed = 30;
	}
}
