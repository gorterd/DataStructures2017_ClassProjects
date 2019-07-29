import java.util.*;

public class Heap<E extends Comparable<E>>
{
   private ArrayList<E> theData;
   
   private int arity;

   public Heap(int k)
   {
      theData = new ArrayList<E>();
      theData.add(null);
      arity = k;
   }

   private void swap(int i, int j)
   {
      E temp = theData.get(i);
      theData.set(i,theData.get(j));
      theData.set(j,temp);
   }

   public boolean offer(E item)
   {
      theData.add(item) ;
      int child = theData.size() - 1;
      int parent = (child + arity -2) / arity;
      while (parent >= 1 && 
             theData.get(parent).compareTo(theData.get(child)) > 0)
      {
         swap(parent,  child);
         child = parent;
         parent = (child + arity - 2) / arity;
      }
      return true;
   }

   public E poll() 
   {
      if (theData.size() == 1)
      {
         return null;
      } 

      E result = theData.get(1);

      if (theData.size() == 2)
      {
         theData.remove(1);
         return result;
      }

      /* Remove the last item from the ArrayList and place it into
         the first position. */
      theData.set(1, theData.remove(theData.size()-1));

      int parent = 1;
      while (true) 
      {
         int curChild = arity*(parent - 1) + 2; 
         if (curChild >= theData.size() )
         {
            break;
         }
         int minChild = curChild;
         curChild ++;
         // Iterates through each child of parent, compares to minChild, and
         //  replaces as minChild if less than minChild.
         for (int i = 1; i < arity; i++){  
            if (curChild < theData.size() && 
             theData.get(minChild).compareTo(
                theData.get(curChild)) > 0)
            {             
               minChild = curChild;
            }
            curChild++;
         }
         // Move smaller child up heap if necessary.
         if (theData.get(parent).compareTo(theData.get(minChild)) > 0)
         {
            swap(parent,minChild);
            parent = minChild;
         } 
         else 
         {
            // Heap property is restored.
            break;
         }
      }
      return result;
   }

   // This method is just here for debugging and grading purposes,
   // do not change it
   public void display()
   {
      for (int i=1; i < theData.size(); i++)
         System.out.print(theData.get(i) + " ");
      System.out.println();
   }

   public static void test(boolean condition) {
      if (!condition) {
         throw new RuntimeException("Test failed.");
      }
   }

   public static void main(String[] args)
   {
      for (int k=2; k < 6; k++)
      {
         System.out.println("------------------------");
         System.out.println("k = " + k);
         Random rand = new Random(55057);
         Heap<Integer> heap = new Heap<Integer>(k);

         // testValues used for testing purposes, to see if values come out of
         // the heap in proper order
         ArrayList<Integer> testValues = new ArrayList<>();
         for (int i=0; i < 100; i++) {
            int nextValue = rand.nextInt(500);
            heap.offer(nextValue);
            testValues.add(nextValue);
         }

         Collections.sort(testValues);

         heap.display();
         for (int i=0; i < 50; i++) {
            int valueRemoved = heap.poll();
            test(valueRemoved == testValues.remove(0));
         }
         heap.display();

         for (int i=0; i < 50; i++) {
            int nextValue = rand.nextInt(500);
            heap.offer(nextValue);
            testValues.add(nextValue);
         }

         Collections.sort(testValues);

         for (int i=0; i < 50; i++) {
            int valueRemoved = heap.poll();
            test(valueRemoved == testValues.remove(0));
         }
         heap.display();

         System.out.println("Tests pass!");
      }
        
   }
}
