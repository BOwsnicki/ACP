package generics.types;

import java.util.ArrayList;
import java.util.List;

public class AnimalShelter {
	List<Animal> l = new ArrayList<>();
	public Animal getAnimal() { return l.get(0); };
	public void addAnimal(Animal a) { l.add(a); };
}
