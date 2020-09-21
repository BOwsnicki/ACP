package generics.types;

import java.util.ArrayList;
import java.util.List;

public class CatShelter extends AnimalShelter {
	List<Cat> l = new ArrayList<>();

	public void addAnimal(Cat c) { l.add(c); };
}


