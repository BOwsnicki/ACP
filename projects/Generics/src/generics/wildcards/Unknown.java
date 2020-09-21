package generics.wildcards;

import java.util.ArrayList;
import java.util.List;
import generics.model.A;
import generics.model.B;
import generics.model.C;

public class Unknown {

	public static void processElements(List<?> elements) {
		for (Object o : elements) {
			System.out.println(o);
		}
	}

	public static void main(String[] args) {
		List<A> dataA = new ArrayList<A>();
		processElements(dataA);

		List<B> dataB = new ArrayList<B>();
		processElements(dataB);

		List<C> dataC = new ArrayList<C>();
		processElements(dataC);

		List<String> dataS = new ArrayList<String>();
		processElements(dataS);
	}
}
