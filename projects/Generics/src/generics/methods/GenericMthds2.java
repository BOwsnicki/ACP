package generics.methods;

import java.util.ArrayList;
import java.util.Collection;

public class GenericMthds2 {

	/*
	 * KAPUT! 
	 * public static void fromArray(Object[] o, Collection<? extends Object> c) {
	 *   for (Object obj : o) {
	 *      c.add(obj); 
	 *   } 
	 * }
	 */

	public static void toArray(Object[] o, Collection<? extends Object> c) {
		// pre: o has enough capacity
		int i = 0;
		for (Object obj : c) {
			o[i++] = obj;
		}
	}

	public static <T> void fromArray(T[] o, Collection<T> c) {
		for (T obj : o) {
			c.add(obj);
		}
	}

	public static void main(String[] args) {
		Integer[] hello = { 42, 200 };
		Collection<Integer> c = new ArrayList<>();
		fromArray(hello, c);
		toArray(hello, c);
	}
}
