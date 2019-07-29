import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Stack;


// CREDIT: Learned about Double.parseDouble() and String.valueOf() methods
//    from 'WhiteFang34' and 'Bhavit S. Sengar', respectively, on Stack Overflow.

public class Lisp{
  
   public static void main(String[] args) throws IOException{
      Stack<String> stack1 = new Stack<String>();
      Stack<String> stack2 = new Stack<String>();
      String current = "";
      String operation;
      Scanner input = new Scanner(new File(args[0]));
      // Continues Lisp algorithm until there is nothing left to read from file.
      while (input.hasNext()){
         // Adds each term in file to first stack, until first ")" is hit.
         while (!current.equals(")")){
            current = input.next();
            stack1.push(current);
         }
         // Now, takes terms from first stack until first "(" is hit, adds to 
         //    second stack for evaluation.
         while(!current.equals("(")){
            current = stack1.pop();
            stack2.push(current);
         }
         // Removes left paranthesis.
         stack2.pop();
         operation = stack2.pop();
         // Performs correct operation on values contained within the parantheses.
         if (operation.equals("+"))
            stack1.push(add(stack2));
         else if (operation.equals("-"))
            stack1.push(sub(stack2));
         else if (operation.equals("*"))
            stack1.push(mult(stack2));
         else if (operation.equals("/"))
            stack1.push(div(stack2));
         else
            System.out.println("Operation error!");
      }
      double result = Double.parseDouble(stack1.pop());
      System.out.print("The result is: ");
      System.out.println(result);
   }
   
   // Takes a stack, adds contained values until end paranthesis is hit.
   public static String add(Stack<String> opStack){
      double sum = Double.parseDouble(opStack.pop());
      String sumString;
      while (!opStack.peek().equals(")")){
         sum += Double.parseDouble(opStack.pop());
      }
      opStack.pop();
      sumString = String.valueOf(sum);
      return sumString; 
   }
   
   // Takes a stack, subtracts from first value all subsequent values  
   //    until end paranthesis is hit.
   public static String sub(Stack<String> opStack){
      double diff = Double.parseDouble(opStack.pop());
      String diffString;
      while (!opStack.peek().equals(")")){
         diff -= Double.parseDouble(opStack.pop());
      }
      opStack.pop();
      diffString = String.valueOf(diff);
      return diffString; 
   }
   
   // Takes a stack, multiplies contained values until end paranthesis is hit.
   public static String mult(Stack<String> opStack){
      double product = Double.parseDouble(opStack.pop());
      String productString;
      while (!opStack.peek().equals(")")){
         product = product * Double.parseDouble(opStack.pop());
      }
      opStack.pop();
      productString = String.valueOf(product);
      return productString; 
   }
   
   // Takes a stack, divides from first value all subsequent values  
   //    until end paranthesis is hit.
   public static String div(Stack<String> opStack){
      double quotient = Double.parseDouble(opStack.pop());
      String quotientString;
      while (!opStack.peek().equals(")")){
         quotient = quotient / Double.parseDouble(opStack.pop());
      }
      opStack.pop();
      quotientString = String.valueOf(quotient);
      return quotientString; 
   }
   
}