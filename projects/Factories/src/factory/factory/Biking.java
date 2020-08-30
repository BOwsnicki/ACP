package factory.factory;

import factory.classes.Bicycle;
import factory.classes.Vehicle;

public class Biking extends PastTime {

	public Biking() {
		super();
	}

	@Override
	protected Vehicle createVehicle() {
		return new Bicycle();
	} 
}
