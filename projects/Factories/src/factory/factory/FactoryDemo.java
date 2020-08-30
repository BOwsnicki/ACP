package factory.factory;

public class FactoryDemo {
	public static void main(String[] args) {
		PastTime p = new Racing();
		System.out.println(p.getVehicle().getClass());
	}
}
