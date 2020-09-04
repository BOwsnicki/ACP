package edu.uwf.cs.acp.project1.db;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uwf.cs.acp.project1.classes.Car;
import edu.uwf.cs.acp.project1.map.DerbyTypeMapper;
import edu.uwf.cs.acp.project1.map.TypeMapper;
import edu.uwf.cs.acp.project1.patterns.CarBuilder;
import edu.uwf.cs.acp.project1.patterns.CarFactory;

public class CarDB {
	static private TypeMapper map = new DerbyTypeMapper();

	private List<Car> cars;
	private Map<String, String> fieldMap;
	private Connection conn;
	private Statement stmt;

	public CarDB(int n) {
		try {
			SimpleDataSource.init();
			conn = SimpleDataSource.getConnection();
			stmt = conn.createStatement();
		} catch (Exception e) {
		}
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

	public String createTableDecl() {
		String result = "Create Table Cars (";
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			result += entry.getKey() + " " + map.mapToDBType(entry.getValue()) + ",";
		}
		return (result.substring(0, result.length() - 1)) + ")";
	}

	private String getValue(Car c, String fieldName, String javaType)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> car = c.getClass();
		Field field = car.getDeclaredField(fieldName);
		field.setAccessible(true);
		String raw = field.get(c).toString();
		if (javaType.equals("class java.lang.String")) {
			raw = "'" + raw + "'";
		}
		return raw;
	}

	public String createInsert(Car c) {
		String result = "Insert Into Cars VALUES (";
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			String value = "???";
			try {
				value = getValue(c, entry.getKey(), entry.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			result += value + ",";
		}
		return (result.substring(0, result.length() - 1)) + ")";
	}

	public void storeCars() {
		try {
			stmt.execute("DROP TABLE Cars");
		} catch (Exception e) {
		}
		try {
			String s =  createTableDecl();
			System.out.println(s);
			stmt.execute(s);
			for (Car c : cars) {
				String s1 = createInsert(c);
				System.out.println(s1);
				stmt.execute(s1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processQuery(String sqlQuery) {
		ResultSet result;
		try {
			result = stmt.executeQuery(sqlQuery);
			ResultSetMetaData rsm = result.getMetaData();
			int cols = rsm.getColumnCount();
			while (result.next()) {
				CarBuilder cb = new CarBuilder().newCar();
				for (int i = 1; i <= cols; i++) {
					cb.addValue(rsm.getColumnName(i), result.getObject(i));
				}
				System.out.println(cb.build());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
