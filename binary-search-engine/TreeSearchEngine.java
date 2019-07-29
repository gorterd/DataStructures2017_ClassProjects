import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class TreeSearchEngine {
   
   //Switch for printing testing output.
   public static boolean testing = true;
   
   public static void main(String[] args) throws IOException {
      Scanner input = new Scanner(new File(args[0]), "ISO-8859-1");
      TreeMapForStrings treemap = new TreeMapForStrings();
      String next = input.next();
      String title;
      String plot;
      int numEntries = 0;
      double t1, t2;
      double loadingTime = 0;
      double addingTime = 0; 
      //Advances scanner to beginning of first movie title.
      while(!next.equals("MV:")){
            next=input.next();
      }
      //Repeats adding entries to treemap until document is complete.
      while (input.hasNext()){
         plot = "";
         t1 = System.nanoTime();
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
         t2 = System.nanoTime();
         loadingTime += (t2 - t1);
         t1 = System.nanoTime();
         treemap.put(title, plot);
         t2 = System.nanoTime();
         addingTime += (t2 - t1);
         numEntries ++;
         if (numEntries % 1000 == 0 && testing){
            System.out.println ("Loaded " + numEntries + " entries...");
         }
         while(!next.equals("MV:") && input.hasNext()){
            next=input.next();
         }
      }
      loadingTime = loadingTime/1000000000;
      addingTime = addingTime/1000000000;
      if (testing){
         System.out.println("Loading Time (s): " + loadingTime);
         System.out.println("Adding Time (s): " + addingTime);
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
         System.out.println("Please input a movie title or prefix:");
         searchKey = keyboard.nextLine();
         System.out.println();
         ArrayList<String> titleArray = treemap.getKeysForPrefix(searchKey);
         String cur;
         if (titleArray.size()==0 && !searchKey.equals("####")){
            System.out.print("Sorry, no movie title matches the input ");
            System.out.println("you gave. Please try again.");
         }
         else if (titleArray.size()>0) {
            for (int i = 0; i < titleArray.size(); i++){
               cur = titleArray.get(i);
               System.out.println("Movie Title: " + cur);
               System.out.println();
               System.out.println("Movie Plot:");
               System.out.println(treemap.getValue(cur));
               System.out.println();
               System.out.println();
            }
         }
      }
   }
}            