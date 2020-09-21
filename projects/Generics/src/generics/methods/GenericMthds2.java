package generics.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GenericMthds2 {
/*	
	public static void fromArray(Object[] o, Collection<Object> c) {
	   for (Object obj : o) {
	      c.add(obj);
	   }
	   System.out.println();
	}
*/
	public static <T> void fromArray(T[] o, Collection<T> c) {
		   for (T obj : o) {
		      c.add(obj);
		   }
		   System.out.println();
		}
	
	public static void main(String[] args) {

		String[] hello = {"hello", "World" };
		Collection<String> c = new ArrayList<>();
		fromArray(hello,c);
	}
}
