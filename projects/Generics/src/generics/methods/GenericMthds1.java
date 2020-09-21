package generics.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericMthds1 {
	
	public static <E> void print(E[] a) {
	   for (E e : a) {
	      System.out.print(e + " ");
	   }
	   System.out.println();
	}


	public static <E> void print(List<E> a) {
		   for (E e : a) {
		      System.out.print(e + " ");
		   }
		   System.out.println();
		}

	public static void main(String[] args) {

		String[] hello = {"hello", "World" };
		print(hello);

		List<String> hl = Arrays.asList(hello); 
		print(hl);
	}
}
