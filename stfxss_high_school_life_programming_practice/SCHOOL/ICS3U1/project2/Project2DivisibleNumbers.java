package project2;

 /*
 * Wonjohn Choi
 * Rockwood,G
 * ICS3U1-02
 * Friday, October 9, 2009
 */
import java.util.*;

public class Project2DivisibleNumbers {
 public static void main(String[] args)
 {
  //make a new Scanner named choi.
  Scanner choi=new Scanner(System.in);
  //Print out that users are welcome.
  System.out.println("Welcome to Wonjohn's counter for DivisibleNumber");
  //Make an empty line
  System.out.println();
  //Ask user to give the smaller integer value to set range.
  System.out.print("Give the first integer value (minimum) to set the range: ");
  //Receive the input that user gives to initialize the value of minLimit.
  int minLimit=choi.nextInt();
  //Ask user to give the bigger integer value to set range.
  System.out.print("Give the second integer value to set the range: ");
  //Receive the input that user gives to initialize the value of maxLimit.
  int maxLimit=choi.nextInt();
  //Ask user to give an integer that will be used to count the number of integers 
  //that are divisible by the divisor.
  System.out.print("Give an integer value that will be used as divisor to find numbers that are evenly divisible: ");
  //Receive the input that user gives to initialize the value of divisor.
  int divisor=choi.nextInt();
  //Make empty lines to make the output neater.
  System.out.println();
  System.out.println();
  System.out.println();
  System.out.println();   
  System.out.println();
  System.out.println();
  System.out.println();   
  System.out.println();
  System.out.println();
  System.out.println();
  //Printout a statement "Evenly divisible numbers are: ".
  System.out.println("Evenly divisible numbers are:");
  //Initialize a integer value counter as 0 and 
  //this will be used to count the number of integers that are divisible by divisor. 
  int counter=0;
  //Create a for loop to count the divisible numbers and 
  //to printout the divisible numbers between minLimit and maxLimit.
  //For the integers between minLimit and maxlimit (stored as 'answer'),
  for(int answer=minLimit;answer<=maxLimit;answer++)
  {
   //if 'answer' is divisible by 'divisor'
   if(answer%divisor==0)
   {
	//counter increases by 1 so that this counts the total number of divisible integers.
    counter=counter+1;
    //Print the integers divisible by 'divisor'
    System.out.print(answer+" ");
   }
  }
  //make an empty line
  System.out.println();
  //'counter' has a value of the divisible integers in the range. 
  //'divisor', 'minLimit' and 'maxLimit' are the given value.
  System.out.println("There are "+counter +" evenly divisible numbers by "+divisor+" between "+minLimit+" and "+maxLimit+".");
 }
}