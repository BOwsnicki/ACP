package generics.wildcards;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ExtendsExample {
	
	private static double sum(Collection<? extends Number> numbers) {
	    double result = 0.0;
	    for (Number num : numbers) result += num.doubleValue();	 
	    return result;
	}
	
	/* KAPUT!
	private static void addElem(Collection<? extends Number> numbers, Number n) {
		numbers.add(n);
	}
	Reason:
		List<Integer> integers = Arrays.asList(2, 4, 6);
		Double x = 3.14;
		addElem(integers, x);
	breaks type safety
	*/
	
	private static void addNULL(Collection<? extends Number> numbers) {
		numbers.add(null); 	// Well, that's helpful!
							// null works for every type...
	}
	
	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(2, 4, 6);
		double sum = sum(integers);
		System.out.println("Sum of integers = " + sum);
		 
		 
		List<Double> doubles = Arrays.asList(3.14, 1.68, 2.94);
		sum = sum(doubles);
		System.out.println("Sum of doubles = " + sum);
		 
		List<Number> numbers = Arrays.<Number>asList(2, 4, 6, 3.14, 1.68, 2.94);
		sum = sum(numbers);
		System.out.println("Sum of numbers = " + sum);
	}
}
