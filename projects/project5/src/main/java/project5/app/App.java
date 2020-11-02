package project5.app;

import java.util.List;
import java.util.Scanner;

import project5.storage.Dealership;
import project5.pojo.Car;

public class App {

	private static List<Car> try1(Dealership d, String what) {
		List<Car> cars = d.getByMake(what);
		if (cars.size() != 0) {
			return cars;
		}
		return d.getBySize(what);
	}
	
	private static List<Car> try2(Dealership d, String what1, String what2) {
		List<Car> cars = d.getByMakeAndSize(what1,what2);
		if (cars.size() != 0) {
			return cars;
		}
		return d.getByMakeAndSize(what2,what1);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Dealership d = new Dealership(20);
		String input;
		System.out.print("customer: ");
		while (!(input = in.nextLine()).contentEquals("quit")) {
			String[] split = input.split(" ");
			List<Car> result;
			if (split.length == 1) {
				result = try1(d,split[0].trim());
			} else {
				result = try2(d,split[0].trim(),split[1].trim());
			}

			for (Car c : result) {
				System.out.println(c);
			}
			System.out.print("customer: ");
		}
	}

}
