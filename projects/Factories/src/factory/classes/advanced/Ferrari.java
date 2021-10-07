package factory.classes.advanced;

public class Ferrari extends Vehicle {
	static {
		register(Ferrari.class);
		maxSpeed = 200;
	}
}
