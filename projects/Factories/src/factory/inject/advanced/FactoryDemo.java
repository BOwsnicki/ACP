package factory.inject.advanced;

public class FactoryDemo {
	public static void main(String[] args) {
		PastTime p = PastTimeFactory.getPastTime(true);
		System.out.println(p.getVehicle().getClass());
	}
}
