package edu.uwf.cs.acp.project1.app;

import edu.uwf.cs.acp.project1.classes.Car;
import edu.uwf.cs.acp.project1.db.CarDB;
import edu.uwf.cs.acp.project1.factory.CarFactory;

public class App {
	public static void main(String[] args) {
		CarDB db = new CarDB();
		db.storeCars();
		db.processQuery("Select * From Cars");
	}
}
