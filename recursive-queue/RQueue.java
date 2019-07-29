
public class RQueue<T> implements CarlQueue<T>{
   
   private T front;
   
   private RQueue<T> inside;
   
   private T rear;
   
   public RQueue(){
      front = null;
      rear = null;
   }
   
   // Adds item to queue
   public void enqueue(T item){
      if (front == null)
         front = item;
      else if (rear == null)
         rear = item;
      else if (inside == null){
         inside = new RQueue<T>();
         inside.enqueue(rear);
         rear = item;
      } else {
         inside.enqueue(rear);
         rear = item;    
      }  
   }
   
    // Returns next item to come out of queue, without changing it.
    // If the queue is empty, return null   
   public T getFront(){
      return front;
   }

    // Removes the next item from the queue. If the queue is empty, returns null.  
   public T dequeue(){
      T tempFront = front;
      if (rear == null)
         front = null;
      else if (inside == null){
         front = rear;
         rear = null;
      } else if (inside.rear == null){
         front = inside.dequeue();
         inside = null;
      } else
         front = inside.dequeue();
      return tempFront;
   }
   
   // Prints the contents of the queue.
   public void display(){
      if (front == null)
         System.out.println("The queue is empty!");
      else if (front != null)
         System.out.println(front);
      if (inside != null)
         inside.display();
      if (rear != null)
         System.out.println(rear); 
   }
    
}