import java.util.Scanner;

// Author: Daniel Gorter
// Description: Human is a user controlled player in SticksGame, which allows
//                the user to make moves, and which displays messages for the
//                user on occasion.
public class Human implements Player
{
   private String name;
   
   private Scanner keyboard = new Scanner(System.in);
   
   // (constructor) Assigns parameter string as player name.
   public Human(String whichPlayer)
   {
      name = whichPlayer;
   }
   
   // Method: move
   // Parameters: numSticks = the number of sticks left in game at move time.
   //             notAuto = n/a for Human (see AI for function)
   // Return:     int - number of sticks the player wants to remove
   // Function:   Prints current game info.
   //             Let's user choose number of sticks to remove.
   public int move(int numSticks, boolean notAuto)
   {
      System.out.print("\nThere are ");
      System.out.print(numSticks);
      System.out.println(" stick(s) on the board.");
      System.out.print(name);
      System.out.print(": How many sticks do you take (1-3)? ");
      int choice = keyboard.nextInt();
      while (choice < 1 || choice > 3 || numSticks < choice)
      {
         if (choice < 1 || choice > 3)
            System.out.println("Please enter a number between 1 and 3.");
         else 
            System.out.println("There aren't that many sticks left. Try again.");
         System.out.print(name);
         System.out.print(": How many sticks do you take (1-3)? ");
         choice = keyboard.nextInt();
      }
      return choice;
   }
   
   // Prints message to start game.
   public void startGame()
   {
      System.out.print(name);
      System.out.println(": Good luck!");  
   }
   
   // At this point, a 'dead' method.
   public void endGame(boolean win, boolean notAuto)
   {
      return;
   }
}