package factory.factory;

import factory.classes.Truck;
import factory.classes.Vehicle;

public class Trucking extends PastTime {

	public Trucking() {
		super();
	}

	@Override
	protected Vehicle createVehicle() {
		return new Truck();
	} 
}
