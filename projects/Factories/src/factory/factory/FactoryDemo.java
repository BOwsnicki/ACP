package factory.factory;

public class FactoryDemo {
	public static void main(String[] args) {
		PastTime p = new Trucking();
		System.out.println(p.getVehicle().getClass());
	}
}
