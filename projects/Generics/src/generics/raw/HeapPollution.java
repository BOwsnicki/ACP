package generics.raw;

import java.util.ArrayList;
import java.util.List;

public class HeapPollution {
	public static void main(String[] args) {
		List list = new ArrayList();
		List<String> ls = list; // Produces unchecked warning

		List s = new ArrayList<Integer>();
		List<String> ss = s; // unchecked warning
		s.add(new Integer(42)); // another unchecked warning
		for (String str : ss) {
			System.out.println(str);
		}
	}

}
