package generics.apps;

import generics.linkedlist.LinkedList;
import generics.linkedlist.ListIterator;
import generics.model.Customer;

public class CustomerList2
{  
   public static void main(String[] args)
   {  
      LinkedList<Customer> clients = new LinkedList<Customer>();
		for(int i = 0; i < 20; i++)
        clients.addFirst(new Customer("name" + i, i + 50));
      ListIterator<Customer> iterator = clients.listIterator();

      while (iterator.hasNext())
      {
         Customer element = iterator.next();
         System.out.println(element + " ");         
      }
      System.out.println();
   }
}