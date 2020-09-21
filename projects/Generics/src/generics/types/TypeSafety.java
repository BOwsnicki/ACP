package generics.types;

public class TypeSafety {
	public static void main(String[] args) {
		AnimalShelter s = new CatShelter();
		s.addAnimal(new Dog());
	}
}
