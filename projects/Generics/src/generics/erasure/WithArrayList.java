package generics.erasure;

import java.util.List;
import java.util.ArrayList;

public class WithArrayList<E> {
	private List<E> items;

	public WithArrayList() {
		items = new ArrayList<>();
	}
}
