package factory.inject;

import java.lang.reflect.Field;

import factory.classes.Bicycle;
import factory.classes.Ferrari;
import factory.classes.Vehicle;

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
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	public static PastTime getPastTime(boolean goFast) {
		PastTime pt;
		if (goFast) {
			pt = new Racing();
			inject(pt, new Ferrari());
		} else {
			pt = new Biking();
			inject(pt, new Bicycle());
		}
		return pt;
	}
}
