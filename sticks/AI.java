import java.util.Scanner;

// Author: Daniel Gorter
// Description: AI is an AI player in SticksGame, which makes moves, 
//                  displays messages, and learns from its choices, so as
//                  to improve every game.
public class AI implements Player
{
   //Toggle for printing behind-the-scenes info, default off.
   private static boolean devTog = false;
   
   private String name;
   
   private Scanner keyboard = new Scanner(System.in);
   
   private int choice;
   
   private int[] choiceArray;
   
   private intHat[] hatArray;
      
   // Method: AI (constructor)
   // Parameters: whichPlayer = Name of player
   //             arraySize = number of sticks to start game
   // Function:   Assigns name to AI.
   //             Creates choiceArray to store each move the AI makes, to remove
   //                1, 2, or 3 sticks, in cell of array corresponding to total
   //                sticks remaining in game.
   //             Creates an array of hats for each possible remaining number of
   //                sticks; each hat stores possible move choices for AI, which
   //                change from game to game (see intHat).
   public AI(String whichPlayer, int arraySize)
   {
      name = whichPlayer;
      choiceArray = new int[arraySize];
      hatArray = new intHat[arraySize];
      for (int i = 0; i < hatArray.length; i++)
         hatArray[i] = new intHat();
      for (int i = 0; i < choiceArray.length; i++)
         choiceArray[i]=0;     
   }
   
   // Method: move
   // Parameters: numSticks = the number of sticks left in game at move time.
   //             notAuto = true only if in game with human (to print move info).
   // Return:     int - number of sticks the AI wants to remove
   // Function:   Chooses a ball (1, 2, or 3) from the hat corresponding to
   //                 the current number of remaining sticks. 
   //             Stores choice in choiceArray (which tracks choices for each game).
   //             Prints info to user in game with human.
   public int move(int numSticks, boolean notAuto)
   {
      choice = hatArray[numSticks-1].getBall(numSticks);
      choiceArray[numSticks-1] = choice;
      if (notAuto)
      {
         System.out.print("\nThere are ");
         System.out.print(numSticks);
         System.out.println(" stick(s) on the board.");
         System.out.print(name);
         System.out.print(" selects ");
         System.out.print(choice);
         System.out.println(" stick(s).");
      }
      return choice;
   }
   
   // Method: startGame
   // Function: Print starting game message.   
   public void startGame()
   {
      System.out.print(name);
      System.out.println(" says, 'I, the AI, will hope to defeat you!'");
   }
   
   // Method: endGame
   // Parameters: 'win' should be true if AI won, false if AI lost
   //             'notAuto' should be true only if in game with human
   // Function:   If AI won, gives more weight to choices made in game
   //                for future decisions.
   //             If AI lost, reduces weight from choices made in game 
   //                for future decisions.  
   //             Also, resets the array that tracks individual game choices
   //                for next game, and prints message if in game with human.         
   public void endGame(boolean win, boolean notAuto)
   {
      if (win)
      {
         for (int i = 0; i < choiceArray.length; i++)
            hatArray[i].addBall(choiceArray[i]);
      }
      else 
      {
         for (int i = 0; i < choiceArray.length; i++)
            hatArray[i].removeBall(choiceArray[i]);
      }
      if (notAuto)
      {
         System.out.println("");
         System.out.print(name);
         System.out.println(" says 'That was fun, thank you!'\n");
         if (devTog)
         {
            System.out.println("Choice array:");
            for (int i = 0; i < choiceArray.length; i++)
            {
               System.out.print(i+1);
               System.out.print(" Ball(s) Left:");
               System.out.println(choiceArray[i]);
            }
            displayStats();
         }
      } 
      for (int i = 0; i < choiceArray.length; i++)
         choiceArray[i]=0;
   }
   
   // Method: displayStats()
   // Purpose: Displays the AI's guts (the contents of each hat).
   //          At this point, only called in endGame if devTog 
   //             is turned on, but could coded in elsewhere.
   private void displayStats()
   {
      System.out.println("\nHat array: ");
      for (int i = 0; i < choiceArray.length; i++)
         hatArray[i].giveContent(i+1);    
   }
}