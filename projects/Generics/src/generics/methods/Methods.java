package generics.methods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Methods {

		public static <E> void print(E[] a)
		{
		   for (E e : a) {
		      System.out.print(e + " ");
		   }
		   System.out.println();
		}
		
		public static void foo(List<Object> l) {
			
		}

		public static void fromArray(Object[] a, Collection<Object> o) {
			for (int i = 0; i < a.length; i++) {
				o.add(a[i]);
			}
		}
	
		/*
		public static void fromArray(Object[] a, Collection<?> o) {
			for (int i = 0; i < a.length; i++) {
				o.add(a[i]);
			}
		}
		*/
		
		public static void main(String[] args) {
			/*
			String[] s = { "Hello", "World!" };
			Collection<String> c = new ArrayList<>();
			fromArray(s,c);
			*/
			List<String> l = new ArrayList<>();
			foo(l);
		}
	}
