package edu.uwf.cs.acp.project1.db;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uwf.cs.acp.project1.classes.Car;
import edu.uwf.cs.acp.project1.factory.CarFactory;
import edu.uwf.cs.acp.project1.map.DerbyTypeMapper;
import edu.uwf.cs.acp.project1.map.TypeMapper;

public class CarDB {
	static private TypeMapper map = new DerbyTypeMapper();

	private List<Car> cars;
	private Map<String, String> fieldMap;

	public CarDB(int n) {
		cars = new ArrayList<Car>();
		for (int i = 0; i < n; i++) {
			cars.add(CarFactory.createRandomCar());
		}
		fieldMap = createMap();
		listCars(cars, System.out);
	}

	public CarDB() {
		this(10);
	}

	private Map<String, String> createMap() {
		Map<String, String> result = new HashMap<String, String>();

		Class<?> c;
		try {
			c = Class.forName("edu.uwf.cs.acp.project1.classes.Car");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		Field f[] = c.getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			result.put(f[i].getName(), f[i].getType().toString());
		}

		// Just to make sure...
		for (Map.Entry<String, String> entry : result.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		return result;
	}

	private static void listCars(List<Car> l, PrintStream s) {
		for (Car c : l) {
			s.println(c);
		}
	}

	private static String findType(String javaType) {
		if (javaType.equals("int")) {
			return "INTEGER";
		}
		if (javaType.equals("class java.lang.String")) {
			return "CHAR(40)";
		}
		return javaType;
	}

	public String createTableDecl() {
		String result = "Create Table Cars (";
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			result += entry.getKey() + " " + map.mapToDBType(entry.getValue()) + ",";
		}
		return (result.substring(0, result.length() - 1)) + ")";
	}

	private String getValue(Car c, String fieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> car = c.getClass();
		Field field = car.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(c).toString();
	}

	public String createInsert(Car c) {
		String result = "Insert Into Cars (";
		for (Map.Entry<String,String> entry : fieldMap.entrySet()) {
			String value = "???";
			try {
				value = getValue(c,entry.getKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
			result += "'"
		// INSERT INTO Test2 VALUES ('Romeo',27, true)
	}
}
