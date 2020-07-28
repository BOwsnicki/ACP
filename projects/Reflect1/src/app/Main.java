package app;

import java.lang.reflect.Field;

public class Main {
	public static void main(String[] args) {
		  Person p = new Person();
		  System.out.println(p.getHairColor()); // prints “brown”

		  Class pClass = p.getClass();
		  try {
		     Field hairColor = pClass.getDeclaredField("hairColor");
		     hairColor.setAccessible(true);
		     hairColor.set(p,"blond");
		  } catch (NoSuchFieldException | SecurityException |  
		           IllegalArgumentException | 
		           IllegalAccessException ex) { }

		  System.out.println(p.getHairColor()); // prints “blond”
	}
}
