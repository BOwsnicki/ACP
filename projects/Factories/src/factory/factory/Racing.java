package factory.factory;

import factory.classes.Ferrari;
import factory.classes.Vehicle;

public class Racing extends PastTime {

	public Racing() {
		super();
	}
	
	@Override
	protected Vehicle createVehicle() {
		return new Ferrari();
	} 
}
