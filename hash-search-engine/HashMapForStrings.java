import java.util.LinkedList;

// A hashmap for entries with string keys and string values.
public class HashMapForStrings {
   
   private LinkedList<Entry>[] array;;
   
   private static final int ARRAY_SIZE = 500;
   
   //An entry within each linked list in the array of linked lists.
   //Contains two pieces of data: entry key and entry value.
   //Idea Credit: Prof David Musicant
   private class Entry {
      
      private String key;
      
      private String value;
      
      private Entry(String key, String value){
         this.key = key;
         this.value = value;
      }
   }
   
   //Constructor
   public HashMapForStrings(){
      array = new LinkedList[ARRAY_SIZE];
   }

   //Inputs a given entry into the hashmap (by parameters key and value). 
   //If entry exists with given key, replaces said entry with given entry.
   //Otherwise, adds at front of linked list.
   public void put(String key, String value){
      Entry entry = new Entry(key,value);
      int hashCode = Math.abs(key.hashCode());
      int index = hashCode % ARRAY_SIZE;
      if (array[index]==null){
         array[index] = new LinkedList<Entry>();
         array[index].add(entry);
      }
      else {
         LinkedList<Entry> list = array[index];
         int size = list.size();
         boolean contains = false;
         for (int i = 0; i < size; i++){
            if (list.get(i).key.equals(key)){
               list.set(i, entry);
               contains = true;
            }
         }
         if (!contains){
            list.add(entry);
         }  
      }
   }
   
   //Gets the value of the entry corresponding to the given key.
   //If no entry with given key exists, returns null.
   public String getValue(String key){
      int hashCode = Math.abs(key.hashCode());
      int index = hashCode % ARRAY_SIZE;
      if (array[index]!=null){
         LinkedList<Entry> list = array[index];
         int size = list.size();
         for (int i = 0; i < size; i++){
            if (list.get(i).key.equals(key)){
               return list.get(i).value;
            }
         }
      }
      return null;
   }
   
   //Takes string as input, outputs an int hash code.
//    private static int hashFunction(String key){
//       int length = key.length();
//       int code = length;
//       int i = 1;
//       while (i < 6 && i <= length){
//          char nextChar = key.charAt(length - i);
//          int next = (int) nextChar;
//          code += (int) next*Math.pow(7, i+1);
//          i++;
//       }
//       return code;
//    }
   
   
   
   //For testing.
   public static void main(String[] args){
      HashMapForStrings testHash = new HashMapForStrings();
      String movie1 = "The Beauty and the Beast";
      String movie2 = "A New Hope";
      String movie3 = "Star Wars 3";
      String movie4 = "The Big Lebowski";
      testHash.put("movie1",movie1);
      testHash.put("movie2",movie2);
      testHash.put("movie3",movie3);
      testHash.put("movie4",movie4);
      System.out.println(testHash.getValue("movie1"));
      System.out.println(testHash.getValue("movie2"));
      System.out.println(testHash.getValue("movie3"));
      System.out.println(testHash.getValue("movie4"));
      System.out.println(testHash.getValue("movie5"));
      testHash.put("movie1","New First Movie");
      System.out.println(testHash.getValue("movie1"));
      testHash.put("movie1movie",movie1);
      testHash.put("movie2movie",movie2);
      testHash.put("movie3movie",movie3);
      testHash.put("movie4movie",movie4);
      System.out.println(testHash.getValue("movie4movie"));
      System.out.println(testHash.getValue("movie3movie"));
      System.out.println(testHash.getValue("movie2movie"));
      System.out.println(testHash.getValue("movie1movie"));
      System.out.println(testHash.getValue("movie5movie"));
          
   }
   
   
}
