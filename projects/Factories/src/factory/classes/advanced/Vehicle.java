package factory.classes.advanced;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
	public static int maxSpeed = -1;
	
	private static List<Class<?>> subClasses 
		= new ArrayList<>();
    	
	
	
	
	
	
	
	protected static void register(Class<?> c) {
    	subClasses.add(c);
    }
    
	
	
	
	
	
	
	
	
	
    public static List<Class<?>> getSubClasses() {
    	return subClasses; // NO!!!!!
    }
	
    
    
    
    
    
    
    
    public int maxSpeed() {
		return maxSpeed;
	}
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
		List<Class<?>> sub = Vehicle.getSubClasses();
		Ferrari f = new Ferrari();
		Ferrari f2 = new Ferrari();
		Bicycle b = new Bicycle();
		System.out.println("List of subclasses of Vehicle:");
		for (Class<?> s : sub) {
			System.out.println(s.getTypeName());
		}
	}
}
