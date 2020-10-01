package generics.wildcards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SuperExample {

	public static void append(Collection<? super Integer> integers, int n) {
	    for (int i = 1; i <= n; i++) {
	        integers.add(i);
	    }
	}
	
	// Seriously??? That works???
	public static void add(Collection<? super Integer> integers, Integer x) {
        integers.add(x);
	}
	
	/* You would assume this works, but NO!
	
	public static void add(Collection<? super Integer> integers, Number x) {
        integers.add(x);
	}
	
	And that makes sense WHY? Ok, same thing:
		List<Integer> integers = Arrays.asList(2, 4, 6);
		Double x = 3.14;
		add(integers,x);
	*/
	
	// Good! Or maybe not...
	public static void add(Collection<? super Integer> integers) {
		add(integers, null);
	}
	
	public static void main(String[] args) {
		List<Number> numbers = new ArrayList<Number>();
		append(numbers, 5);
		numbers.add(6.789);		 
		System.out.println(numbers);
	}
}
