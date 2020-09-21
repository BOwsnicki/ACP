package generics.erasure;

public class WithArray<E> {
	private static final int MAX = 10;
	private E[] items;

	public WithArray() {
		items = new E[MAX];
	}
}
