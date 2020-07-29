package rental;


import java.lang.reflect.*;
import java.util.Arrays;

public class RentalReflection2 {


public void reflectDemos()
{
 // Obtain the class object if we know the name of the class
        Class<RentCar> rental = RentCar.class;
        try {
            // get the absolute name of the class
            String rentalClassPackage = rental.getName();
            System.out.println("Class Name is: " + rentalClassPackage);

            // get the simple name of the class (without package info)
            String rentalClassNoPackage = rental.getSimpleName();
            System.out.println("Class Name without package is: " + rentalClassNoPackage);

            // get the package name of the class
            Package rentalPackage = rental.getPackage();
            System.out.println("Package Name is: " + rentalPackage);

            // get all the constructors of the class
            Constructor[] constructors = rental.getConstructors();
            System.out.println("Constructors are: " + Arrays.toString(constructors));

            // get constructor with specific argument
            Constructor<RentCar> constructor = rental.getConstructor(Integer.TYPE);

            // initializing an object of the RentCar class
            RentCar rent = constructor.newInstance(455);

            // get all methods of the class including declared methods of superclasses
            // these are class Car and java.lang.Object
            Method[] allmethods = rental.getMethods();
            System.out.println("All Inherited and Defined Methods are: " + Arrays.toString(allmethods));
            for (Method method : allmethods) {
                System.out.println("  method = " + method.toString());
            }

            // get all methods declared in the class
            // but excludes inherited methods.
            Method[] declaredMethods = rental.getDeclaredMethods();
            System.out.println("Declared Methods are: "
                    + Arrays.toString(declaredMethods));
            for (Method dmethod : declaredMethods) {
                System.out.println("  declared method name = " + dmethod.getName());
            }
 
            // get method with specific name and parameters
            Method oneMethod = rental.getMethod("computeRentalCost",
                    new Class[] { Integer.TYPE });
            System.out.println("Method accessed by getMethod statement is: " + oneMethod);

            // call computeRentalCost method with parameter int
            oneMethod.invoke(rent, 4);

            // get all the parameters of computeRentalCost
            Class[] parameterTypes = oneMethod.getParameterTypes();
            System.out.println("Parameter types of computeRentalCost() are: "
                    + Arrays.toString(parameterTypes));

            // get the return type of computeRentalCost
            Class returnType = oneMethod.getReturnType();
            System.out.println("Return type is: " + returnType);

            // gets all the public instance fields of the class RentCar
            Field[] fields = rental.getFields();

            System.out.println("Public Fields are: ");
            for (Field oneField : fields) {
                // get public field name
                Field field = rental.getField(oneField.getName());
                String fieldname = field.getName();
                System.out.println("  Field name is: " + fieldname);

                // get public field type
                Object fieldType = field.getType();
                System.out.println("  Type of field " + fieldname + " is: "
                        + fieldType);

                // get public field value
                Object value = field.get(rent);
                System.out.println("  Value of field " + fieldname + " is: "
                        + value);

            }


            // How to access private member fields of the class
            // getDeclaredField() returns the private field
            Field privateField = RentCar.class.getDeclaredField("type");
            String name = privateField.getName();
            System.out.println("One private Fieldname is: " + name);
            // makes this private field instance accessible
            // for reflection use only, not normal code
            privateField.setAccessible(true);

            // get the value of this private field
            String fieldValue = (String) privateField.get(rent);
            System.out.println("fieldValue before set = " + fieldValue);
            
            // change the value of the private field from somewhare else!!
            privateField.set(rent,"small");
            fieldValue = (String) privateField.get(rent);
            System.out.println("fieldValue after set = " + fieldValue);
            
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
      RentalReflection2 re = new RentalReflection2();
      re.reflectDemos();
    }
}