package generics.apps;

import generics.linkedlist.LinkedListSimple;
import generics.linkedlist.ListIteratorSimple;
import generics.model.Customer;

public class CustomerList2
{  
   public static void main(String[] args)
   {  
      LinkedListSimple<Customer> clients = new LinkedListSimple<Customer>();
		for(int i = 0; i < 20; i++)
        clients.addFirst(new Customer("name" + i, i + 50));
      ListIteratorSimple<Customer> iterator = clients.listIterator();

      while (iterator.hasNext())
      {
         Customer element = iterator.next();
         System.out.println(element + " ");         
      }
      System.out.println();
   }
}