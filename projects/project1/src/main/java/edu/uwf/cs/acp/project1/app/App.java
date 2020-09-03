package edu.uwf.cs.acp.project1.app;

import edu.uwf.cs.acp.project1.db.CarDB;

public class App {
	public static void main(String[] args) {
		CarDB db = new CarDB();
		System.out.println(db.createTableDecl());
	}
}
