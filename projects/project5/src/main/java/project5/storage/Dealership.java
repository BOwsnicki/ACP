package project5.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import project5.factory.CarFactory;
import project5.pojo.Car;

public class Dealership {
	private List<Car> showRoom;
	
	public Dealership(int n) {
		showRoom = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			showRoom.add(CarFactory.createRandomCar());
		}
	}

	private List<Car> select(Predicate<Car> p) {
		return showRoom.stream().filter(p).collect(Collectors.<Car>toList());
	}
	
	public void addCar(Car c) {
		showRoom.add(c);
	}
	
	public List<Car> getByMake(String make) {
		return select(c -> c.getMake().equals(make));
	}
	
	public List<Car> getBySize(String size) {
		return select(c -> c.getSize().equals(size));
	}
	
	public List<Car> getByMakeAndSize(String make, String size) {
		return select(c -> (c.getMake().equals(make)) && (c.getSize().equals(size))); 
	}
	
	
	public static void main(String[] args) {
		Dealership d = new Dealership(40);
		// List<Car> cars = d.getByMake("Nissan");
		// List<Car> cars = d.getBySize("compact");
		List<Car> cars = d.getByMakeAndSize("Nissan","compact");
		for (Car c : cars) {
			System.out.println(c);
		}
	}
}
