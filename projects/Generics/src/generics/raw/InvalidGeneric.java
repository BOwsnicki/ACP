package generics.raw;

public class InvalidGeneric<E> {
	private E[] many;
	public InvalidGeneric(int max) {
		many = new E[max];				// nope
		// many = (E[])new Object[max];	// yep	
	}
}

