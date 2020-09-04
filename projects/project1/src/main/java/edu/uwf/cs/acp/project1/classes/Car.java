package edu.uwf.cs.acp.project1.classes;

public class Car {
	private String make;
	private String size;
	private int weight;
	private int horsePower;
	
	public Car(String make, String size, int weight, int horsePower) {
		super();
		this.make = make;
		this.size = size;
		this.weight = weight;
		this.horsePower = horsePower;
	}

	public Car () {
		this(null, null, 0, 0);
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", size=" + size + ", weight=" + weight + ", horsePower=" + horsePower + "]";
	}
}
