package factory.inject;

public class FactoryDemo {
	public static void main(String[] args) {
		PastTime p = PastTimeFactory.getPastTime(false);
		System.out.println(p.getVehicle().getClass());
	}
}
