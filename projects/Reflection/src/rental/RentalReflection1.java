package rental;

import java.lang.reflect.*;

public class RentalReflection1 {

	public RentalReflection1(String className) {
		try {
			Class c = Class.forName(className);
			Method m[] = c.getMethods();
			System.out.println("Methods:");
			for (int i = 0; i < m.length; i++)
				System.out.println("  " + m[i].toString());

			Constructor con[] = c.getConstructors();
			System.out.println("constructors:");
			for (int i = 0; i < con.length; i++)
				System.out.println("  " + con[i].toString());

			Field f[] = c.getDeclaredFields();
			System.out.println("fields:");
			for (int i = 0; i < f.length; i++)
				System.out.println("  " + f[i].toString());
		} catch (Throwable e) {
			System.err.println(e);
		}
	}

	public static void main(String args[]) {
		RentalReflection1 re1 = new RentalReflection1("rental.RentCar");
	}
}