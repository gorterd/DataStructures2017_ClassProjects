import java.util.Scanner;

// Game of sticks, main method
// Written by Dave Musicant
//    and Daniel Gorter
// 1/18/2017


public class SticksGame
{
   // Plays a game of human vs AI, with option to play again.
   public static void playGameOnce(Player player1, Player player2, int totalSticks)
   {
      int initSticks = totalSticks;
      System.out.println("");
      player1.startGame();
      player2.startGame();
      //Players keep making moves until there are no sticks left
      while (totalSticks >= 1)
      {
         totalSticks -= player1.move(totalSticks, true);
         // Checks to see if Player 1 loses 
         if (totalSticks == 0)
         {
            System.out.println("\nPlayer 1: You lose.");
            System.out.println("Player 2: You win!");
            player1.endGame(false, true);
            player2.endGame(true, true);
            break;
         }
         totalSticks -= player2.move(totalSticks, true);
         // Checks if Player2 loses
         if (totalSticks == 0)
         {
            System.out.println("\nPlayer 2: You lose.");
            System.out.println("Player 1: You win!");
            player1.endGame(true, true);
            player2.endGame(false, true);
         }
      }  
      //Allows user to play again.
      System.out.println("Would you like to play again? (y)es or (n)o");
      Scanner keyboard = new Scanner(System.in);
      String inp = keyboard.next();
      while (!(inp.equals("y") || inp.equals("n")))
      {
         System.out.println("Please type either (y) or (n).");
         inp = keyboard.next();
      }
      if (inp.equals("y"))
         playGameOnce(player1, player2, initSticks);   
      else
         return;    
   }
   
   //Sets two AI players against each other in 1 million games, returns Player 2
   // from the game, with all that it has learned.
   public static Player trainAI(Player player1, Player player2, int totalSticks)
   {   
      int sticks;
      for (int i = 0; i < 1000000; i++)
      {
         sticks = totalSticks;
         while (sticks >= 1)
         {
            sticks -= player1.move(sticks, false);
            if (sticks == 0)
            {
               player1.endGame(false, false);
               player2.endGame(true, false);
               break;
            }
            sticks -= player2.move(sticks, false);
            if (sticks == 0)
            {
               player1.endGame(true, false);
               player2.endGame(false, false);
            }
         }  
      }
      return player2;
   }
   
   public static void main(String[] args)
   {
      // Initial setup
      System.out.println("Welcome to the game of sticks!");
      Scanner scanner = new Scanner(System.in);
      System.out.print("How many sticks are there on the table initally? (10-100)? ");
      int totalSticks = scanner.nextInt();
      while (totalSticks < 10 || totalSticks > 100)
      {
         System.out.println("Please enter a number between 10 and 100.");
         System.out.print("How many sticks are there on the table initally? (10-100)? ");
         totalSticks = scanner.nextInt();
      }
      
      System.out.println("Options:");
      System.out.println("Play against a friend (1)");
      System.out.println("Play against the computer (2)");
      System.out.println("Play against the trained computer (3)");
      System.out.print("Which option do you take (1-3)? ");
      int option = scanner.nextInt();
    
      // Choose which game: 1 = human v human, 2 = human v AI, 2 = human vs 
      //    trained AI. 
      while (option < 1 || option > 3)
      {
         System.out.println("Bad choice. Try again.");
         option = scanner.nextInt();
      }
      if (option == 1)
      {
         Player player1 = new Human("Player 1");
         Player player2 = new Human("Player 2");
         playGameOnce(player1, player2, totalSticks);
      }
      else if (option == 2)
      {
         Player player1 = new Human("Player 1");
         Player player2 = new AI("Player 2", totalSticks);
         playGameOnce(player1, player2, totalSticks);
      }
      else if (option == 3)
      {
         System.out.println("Training...");
         Player player1 = new AI("Player 1", totalSticks);
         Player player2 = new AI("Player 2", totalSticks);
         player2 = trainAI(player1, player2, totalSticks);
         player1 = new Human("Player 1");
         playGameOnce(player1, player2, totalSticks);
      }         
   }
}