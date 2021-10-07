package factory.inject.advanced;

import java.lang.reflect.Field;
import factory.classes.advanced.Vehicle;

public class PastTimeFactory {

	private static Field getFieldTransitive(Class<?> c, String name)
			throws NoSuchFieldException, SecurityException, NullPointerException {
		if (c == null) {
			throw new NoSuchFieldException();
		}
		try {
			return c.getDeclaredField(name);
		} catch (NoSuchFieldException ex) {
			return getFieldTransitive(c.getSuperclass(), name);
		}
	}

	private static void inject(PastTime pt, Vehicle v) {
		Class<?> pClass = pt.getClass();
		try {
			Field f = getFieldTransitive(pClass, "v");
			f.setAccessible(true);
			f.set(pt, v);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
				| NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	private static Class<?> fastestVehicle() {
		int fastestSpeed = -1;
		Class<?> fastest = null;
		try {
			for (Class sub : Vehicle.getSubClasses()) {
				Field speedField = sub.getField("maxSpeed");
				int speed = speedField.getInt(null);
				if (speed > fastestSpeed) {
					fastestSpeed = speed;
					fastest = sub;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return fastest;
	}

	private static Class<?> slowestVehicle() {
		int slowestSpeed = Integer.MAX_VALUE;
		Class<?> slowest = null;
		try {
			for (Class sub : Vehicle.getSubClasses()) {
				Field speedField = sub.getField("maxSpeed");
				int speed = speedField.getInt(null);
				if (speed < slowestSpeed) {
					slowestSpeed = speed;
					slowest = sub;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return slowest;
	}
	
	public static PastTime getPastTime(boolean goFast) {
		PastTime pt;
		if (goFast) {
			pt = new Racing();
			Class<?> fastest = fastestVehicle();
			inject(pt,fastest.newInstance());
			// inject(pt, new Ferrari());
		} else {
			pt = new Biking();
			inject(pt, new Bicycle());
		}
		return pt;
	}
}
