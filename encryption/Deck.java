import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Deck {
   
   //Card in deck, with value, and pointers to cards above and below.
   private class Node {
      
      private int value;
      
      private Node above;
      
      private Node below;
      
      private Node(int val){
         value = val;
      }
      
   }
   
   //Top card in deck
   private Node top;
   
   //Bottom card in deck
   private Node bottom;
   
   //For testing purposes; if true, will make several print statements
   private boolean test = false;
   
   //Credit: Learned Integer.parseInt() from user 'smas' on Stack Overflow
   //Constructs new Deck using file provided in parameter, each card's value is an 
   //    integer corresponding to each space-separated number in the file.
   //    The top of the deck corresponds to the first number in the file, bottom to last.
   public Deck(String filename) throws IOException{
      Scanner input = new Scanner(new File(filename));
      top = new Node(Integer.parseInt(input.next()));
      bottom = top;
      while (input.hasNext()){
         bottom.below = new Node(Integer.parseInt(input.next()));
         bottom.below.above = bottom;
         bottom = bottom.below;
      }
      bottom.below = top;
      top.above = bottom;
   }
   
   //Prints n cards of deck, starting from the top and moving down, 
   //    wrapping around if necessary.         
   public void print(int n){
      Node current = top;
      for (int i = 0; i < n; i++){
         System.out.print(current.value);
         if ( (i+1) != n)
            System.out.print(" ");
         current = current.below;
      }
      System.out.println();
   }
   
   //Prints n cards of deck, starting from the back and moving up, 
   //    wrapping around if necessary.
   public void printBackwards(int n){
      Node current = bottom;
      for (int i = 0; i < n; i++){
         System.out.print(current.value);
         if ( (i+1) != n)
            System.out.print(" ");
         current = current.above;
      }
      System.out.println();
   }
   
   // Shuffles the deck using 5-step process, then returns a value for use
   //    in encryption/decryption
   public int nextKeyValue(){
      if (test){
         print(29);
         printBackwards(29);
      }
      //Step 1: swap Joker A with card below it
      Node jokerA = findA();
      swap(jokerA, jokerA.below);
      jokerA = jokerA.below;
      if (test){
         print(29);
         printBackwards(29);
      }
      //Step 2: swap Joker B with card below it, twice
      Node jokerB = findB();
      swap(jokerB, jokerB.below);
      swap(jokerB.below, jokerB.below.below);
      jokerA = findA();
      jokerB = findB();
      if (test){
         print(29);
         printBackwards(29);
      }
      //Step 3: move cards above top joker to bottom, below bottom joker
      //          to top
      if (jokerA == findTopJoker()){
         tripleCut(jokerA, jokerB);
      }
      else {
         tripleCut(jokerB, jokerA);
      }
      if (test){
         print(29);
         printBackwards(29);
      }
      //Step 4: moving n cards to bottom, where n is the value of the bottom card,
      //             and then place original bottom card back at bottom
      moveToBottom(bottom.value);
      if (test){
         print(29);
         printBackwards(29);
      }
      //Step 5: look at (n+1)st card, where n is the value of the top card; if it's
      //          a joker, repeat all five steps; otherwise, that value is returned
      Node current = top;
      int topVal = top.value;
      if (topVal == 28)
         topVal = 27;
      for (int i = 0; i < topVal; i++)
         current = current.below;
      if ((current.value == 27) || (current.value == 28)){
         return nextKeyValue();
      }
      else
         return current.value;
   }
   
   // Swaps two cards
   private void swap(Node card1, Node card2){
      int tempVal = card1.value;
      card1.value = card2.value;
      card2.value = tempVal;
   }
   
   // Finds and returns joker A
   private Node findA(){
      Node current = top;
      while(current.value != 27)
         current = current.below;
      return current;
   }
   
   // Finds and returns joker B
   private Node findB(){
      Node current = top;
      while(current.value != 28)
         current = current.below;
      return current;
   }
   
   // Finds and returns topmost joker
   private Node findTopJoker(){
      Node current = top;
      while((current.value != 27) && (current.value != 28))
         current = current.below;
      return current;
   }
   
   // Implements triple cut (see nextKeyValue() for details)
   private void tripleCut(Node joker1, Node joker2){
      Node newBottom;
      Node newTop;
      if (joker1 != top){
         newBottom = joker1.above;
         top.above = joker2;
      } 
      else {
         newBottom = joker2;
      }
      if (joker2 != bottom){
         newTop = joker2.below;
         bottom.below = joker1;
      }
      else {
         newTop = joker1;
      }
      newBottom.below = newTop;
      newTop.above = newBottom;
      joker1.above = bottom;
      joker2.below = top;
      top = newTop;
      bottom = newBottom;
   }
   
   // Implements step 4 of nextKeyValue()
   private void moveToBottom(int numCards){
      Node stackBottom = top;
      if (numCards == 28)
         numCards = 27;
      for (int i = 0; i < (numCards - 1); i++)
         stackBottom = stackBottom.below;
      if (!stackBottom.equals(bottom)){
         top.above = bottom.above;
         bottom.above.below = top;
         top = stackBottom.below;
         top.above = bottom;
         stackBottom.below = bottom;
         bottom.above = stackBottom;
         bottom.below = top;
      }
   }

}