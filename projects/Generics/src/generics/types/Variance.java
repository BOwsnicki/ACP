package generics.types;

import java.util.ArrayList;
import java.util.List;

import generics.model.A;
import generics.model.B;

public class Variance {
	public static void main(String[] args) {
		
		A[] arrayA = new B[100];	// ← Compiler? Runtime?
		arrayA[0] = new A();		// ← Compiler? Runtime?
		
	// List<A> alA = new ArrayList<B>();
	// alA.add(new A());
	}
}
