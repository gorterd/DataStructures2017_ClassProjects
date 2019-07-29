import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class HashSearchEngine {
   
   //Switch for printing testing output.
   public static boolean testing = true;
   
   public static void main(String[] args) throws IOException {
      Scanner input = new Scanner(new File(args[0]), "ISO-8859-1");
      HashMapForStringz hashmap = new HashMapForStringz();
      String next = input.next();
      String title;
      String plot;
      long start = System.currentTimeMillis();
      int numEntries = 0;
      //Advances scanner to beginning of first movie title.
      while(!next.equals("MV:")){
            next=input.next();
      }
      //Repeats adding entries to hashmap until document is complete.
      while (input.hasNext()){
         plot = "";
         title = input.nextLine().trim();
         input.nextLine();
         next = input.next();
         while (next.equals("PL:")){
            plot += input.nextLine().trim() + "\n";
            if (input.hasNext()){
               next = input.next();
            }
            else {
               break;
            }
         }
         hashmap.put(title, plot);
         numEntries ++;
         if (numEntries % 1000 == 0 && testing){
            System.out.println ("Loaded " + numEntries + " entries...");
         }
         while(!next.equals("MV:") && input.hasNext()){
            next=input.next();
         }
      }
      //Memory and runtime testing
      long end = System.currentTimeMillis();
      double time = (end - start)/1000;
      Runtime runtime = Runtime.getRuntime();
      long roughEstimate = runtime.totalMemory() - runtime.freeMemory();
      if (testing){
         System.out.println("Runtime (s) = " + time);
         System.out.println("Memory = " + roughEstimate);
      }
      System.out.println();
      Scanner keyboard = new Scanner(System.in);
      String searchKey = "";
      // The search key *must* match the movie title exactly as 
      //    given in the source document. This will generally look
      //    like (based on shortened snippet of document), for a movie:
      //    "[title]" ([year])
      //    and for an episode in a show:
      //    "[show]" ([year]) {[episode] ([season#].[episode#])}
      while (!searchKey.equals("####")){
         System.out.println("Please input a movie title:");
         searchKey = keyboard.nextLine();
         System.out.println();
         plot = hashmap.getValue(searchKey);
         if (plot==null && !searchKey.equals("####")){
            System.out.print("Sorry, no movie title matches the title ");
            System.out.println("you gave. Please try again.");
         }
         else if (plot!=null) {
            System.out.println("Movie Plot:");
            System.out.println(plot);
            System.out.println();
         }
      }
   }
}            