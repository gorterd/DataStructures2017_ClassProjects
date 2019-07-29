import java.util.Random;

// Author: Daniel Gorter
// Description: Stores three values, each of which are weights corresponding
//                to possible move decisions in SticksGame (remove 1, 2, or 3
//                sticks). Uses metaphor of hat, storing balls, each of which
//                is a possible choice, to remove 1, 2, or 3 sticks. Let's
//                you remove a 'ball' at random: the more balls of any particular
//                choice, the more likely you are to remove it.
public class intHat {

   private int oneCount;
   
   private int twoCount;
   
   private int threeCount;
   
   private int sum;
   
   // (constructor) Initializes hat with 1 ball for 1, 2, and 3 sticks each.
   //               Sets sum of balls to 3.
   public intHat()
   {
      oneCount = 1;
      twoCount = 1;
      threeCount = 1;
      sum = 3;
   }
      
   // Adds a ball (1, 2, or 3 sticks, determined by parameter 'ball'), 
   //    and increases sum of total balls by 1. Returns false in case of error.
   public boolean addBall(int ball)
   {
      sum++;
      if (ball==1)
      {
         oneCount++;
         return true;
      }
      else if (ball==2)
      {
         twoCount++;
         return true;
      }
      else if (ball==3)
      {
         threeCount++;
         return true;
      }
      else{
         sum--;
         return false;
      }
   }
   
   // Removes a ball (1, 2, or 3 sticks, determined by parameter 'ball'), 
   //    and decreases sum of total balls by 1, except if there is only
   //    one remaining ball for the choice provided by the parameter.
   //    In this case, does nothing and returns false. Also returns false
   //       in case of error.
   public boolean removeBall(int ball)
   {
      sum --;
      if (ball==1 && oneCount > 1)
      {
         oneCount--;
         return true;
      }
      else if (ball==2 && twoCount > 1)
      {
         twoCount--;
         return true;
      }
      else if (ball==3 && threeCount > 1)
      {
         threeCount--;
         return true;
      }
      else
      {
         sum++;
         return false;
      }
   }
      
   // Returns a ball at random from the hat. Takes numSticks as parameter
   //    so as to return a choice to remove more balls than remain in 
   //    the game.
   public int getBall(int numSticks)
   {
      Random rand = new Random();
      int max;
      if (numSticks > 2)
         max = sum;
      else if (numSticks == 2)
         max = oneCount + twoCount;
      else
         return 1;
      int random = rand.nextInt(max);
      if (random < oneCount)
         return 1;
      else if (random < oneCount + twoCount)
         return 2;
      else
         return 3;   
   }
   
   // Prints content of hat, for testing purposes.
   public void giveContent(int whichHat)
   {
      System.out.print("Hat ");
      System.out.print(whichHat);
      System.out.print(": ");
      System.out.print(oneCount);
      System.out.print(", ");
      System.out.print(twoCount);
      System.out.print(", ");
      System.out.println(threeCount);
   }
}