package edu.uwf.cs.acp.project1.app;

import edu.uwf.cs.acp.project1.classes.Car;
import edu.uwf.cs.acp.project1.db.CarDB;
import edu.uwf.cs.acp.project1.patterns.CarFactory;

public class App {
	public static void main(String[] args) {
		CarDB db = new CarDB();
		db.storeCars();

		System.out.println("Select * From Cars");
		db.processQuery("Select * From Cars");

		System.out.println("\nSelect * From Cars Where make = 'Ford'");
		db.processQuery("Select * From Cars Where make = 'Ford'");
		
		System.out.println("\nSelect * From Cars Where horsePower < 100");
		db.processQuery("Select * From Cars Where horsePower < 100");
	}
}
