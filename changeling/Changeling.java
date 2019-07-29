import java.io.IOException;
import java.io.File;
import java.util.*;

public class Changeling {
   
   // A graph, wherein each key is a node, and the value associated with
   //    a given key is its adjacency list, implemented with a hash map.
   private static Map<String, Set<String>> graph = new HashMap<>();
   
   // Builds a graph from the file provided, with each word being a node,
   //    and with an edge between any two words of the same length that
   //    differ by one letter. We can assume, given the assignment
   // Then, sees if you can get from provided start word (args[1]) to 
   //    end word (args[2]), changing one letter at a time, determining
   //    a solution by finding the shortest path from start word to end
   //    word in the aforementioned graph. Prints this solution, if it exists.
   //    Otherwise, will print an error message.
   public static void main(String[] args) {
      try {
         if (args.length !=3){
            throw new IOException();
         }
         if (args[1].length() != args[2].length()){
            throw new IOException();
         }
         File wordfile = new File(args[0]);
         Scanner wordlist = new Scanner(wordfile);
         
         //Builds all the nodes of the graph, with null adjacency lists to start.
         while (wordlist.hasNext()){
            graph.put(wordlist.next(), null);
         }
         wordlist = new Scanner(wordfile);
         
         //For each word, builds an adjacency list.
         while (wordlist.hasNext()){
            addConnections(wordlist.next());
         }
         
         //Finds best solution from provided start word to end word and prints it.
         String solution = getSolution(args[1],args[2]);
         System.out.println(solution);     
      } 
      catch(IOException e) {
         System.out.println("Sorry, the input you provided was not usable.");
         System.out.print("This program accepts 3 arguments:");
         System.out.println(" [wordlist file] [word 1] [word 2]"); 
         System.out.println("Also, [word 1] and [word 2] must have the same length."); 
      }
   }
   
   // If given start and end words are in the word database (nodes in graph),
   //    finds shortest path from start to end word, changing one letter
   //    at a time, then returns a string of words from start to end,
   //    separated by commas.
   private static String getSolution(String word1, String word2){
      String solution;
      if (!graph.containsKey(word1)){
         solution = word1 + " is not a word, sorry.";
      } else if (!graph.containsKey(word2)){
         solution = word2 + " is not a word, sorry.";
      }
      
      // Implements a breadth-first traversal, tracking the path through
      //    each node using a hashmap with keys being visited nodes and 
      //    values being the node previous to the key in the path that
      //    goes through the key. The traversal stops when the first path
      //    hits the end word.
      // Credit for much of this code goes to David Musicant, whose
      //    in-class code for finding the shortest path from one node
      //    to another in a graph I modified.
      else {
         Queue<String> queue = new ArrayDeque<String>();
         Map<String, String> visited = new HashMap<>();
         boolean done = false;
         queue.offer(word1);
         visited.put(word1, null);
         while (!queue.isEmpty() && !done) {
            String curWord = queue.remove();
            Set<String> curSet = graph.get(curWord);
            Iterator<String> adjacent = curSet.iterator();
            String otherWord;
            while (adjacent.hasNext() && !done){
               otherWord = adjacent.next();
               if (!visited.containsKey(otherWord)){
                  visited.put(otherWord, curWord);
                  queue.offer(otherWord);
                  if (otherWord.equals(word2)){
                     done = true;
                  }
               }   
            }
         }
         
         // If the end word was successfully reached, recreates path
         //    from start to end word as a string of words separated by
         //    commas, by going backwards from end to 
         //    to start using visited hashmap.
         if (done){
            String curWord = word2;
            solution = word2;
            while (!curWord.equals(word1)){
               solution = visited.get(curWord) + ", " + solution;
               curWord = visited.get(curWord);
            }
         } else {
            solution = "Sorry, there's no solution for those words.";
         }
      }
      return solution;
   }
   
   // Builds an adjacency list for the given word, by going through each
   //    possible string that differs from the given word by one letter,
   //    and if this string is a word in the graph, then it is added
   //    to the given word's adjacency list.
   // Ends by replacing the value for the given word in the graph, which
   //    was previously null, with the built adjacency list.
   private static void addConnections(String word){
      char curLetter;
      String firstHalf;
      String secondHalf;
      String tempWord;
      char tempChar;
      Set<String> edges = new HashSet<>();
      for (int i = 0; i < word.length(); i++){
         curLetter = word.charAt(i);
         firstHalf = word.substring(0,i);
    	 secondHalf = word.substring(i+1, word.length());
         for (int j = 97; j < 123; j++){
            tempChar = (char) j;
            tempWord = firstHalf + tempChar + secondHalf;
            if (graph.containsKey(tempWord)
                  && tempChar != curLetter){                    
               edges.add(tempWord);
            }      
         }  
      }
      graph.replace(word, edges);
   }

}
