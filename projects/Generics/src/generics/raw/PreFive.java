package generics.raw;

import java.util.ArrayList;
import java.util.List;

public class PreFive {
	public static void main(String[] args) {
		List l = new ArrayList();
		l.add("foo");
		l.add(22);
		for (Object o : l) {
			System.out.println(o); // believe it or not that works!
		}
	}
}
