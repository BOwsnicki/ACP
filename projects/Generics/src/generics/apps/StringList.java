package generics.apps;

import generics.linkedlist.LinkedListSimple;
import generics.linkedlist.ListIteratorSimple;


public class StringList {
	public static void main(String[] args) {
		LinkedListSimple<String> staff = new LinkedListSimple<>();
		staff.addFirst("Tom");
		staff.addFirst("Romeo");
		staff.addFirst("Harry");
		staff.addFirst("Dick");
		// staff.addFirst(new Integer(10));
		// | in the comments indicates the iterator position

		ListIteratorSimple<String> iterator = staff.listIterator(); // |DHRT
		iterator.next(); // D|HRT
		iterator.next(); // DH|RT

		// Add more elements after second element

		iterator.add("Juliet"); // DHJ|RT
		iterator.add("Nina"); // DHJN|RT

		iterator.next(); // DHJNR|T

		// Remove last traversed element

		iterator.remove(); // DHJN|T

		// Print all elements

		iterator = staff.listIterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			System.out.print(element + " ");
		}
		System.out.println();
		System.out.println("Expected: Dick Harry Juliet Nina Tom");
	}
}
