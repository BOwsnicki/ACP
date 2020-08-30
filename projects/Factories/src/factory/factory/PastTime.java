package factory.factory;

import factory.classes.Vehicle;

public abstract class PastTime {
	private Vehicle v;

	public PastTime() {
		v = createVehicle();
	}
	public Vehicle getVehicle() {
		return v;
	}
	protected Vehicle createVehicle() {
		return null;
	}
}
