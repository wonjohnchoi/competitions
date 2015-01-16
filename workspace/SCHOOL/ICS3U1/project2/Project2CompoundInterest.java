package project2;

/*
 * Wonjohn Choi
 * Rockwood,G
 * ICS3U1-02
 * Friday, October 12, 2009
 */

import java.util.*;

public class Project2CompoundInterest{
 public static void main(String[] args)
 {
  //create a new Scanner named choi.
  Scanner choi=new Scanner(System.in);
  //say that "WELCOME"
  System.out.println("Welcome to Wonjohn's GIC calculator");
  //make an empty line to make this neater
  System.out.println();
  //give an instruction that you need to put $ and % for your inputs.
  System.out.println("Beware that you only need to put number value without $ and % sign");
  //make an empty line to make this neater
  System.out.println();
  //Ask user to give an initial value of deposit
  System.out.println("How much initial money do you expect to invest into a GIC(Guaranteed Investment Certificate)?");
  System.out.print("$");
  //User gives the value of initial deposit by initializing double deposit.
  double deposit=choi.nextDouble();
  //Ask user to give an interest rate.
  System.out.println("How much interest rate will the GIC have? (write in percentage form)?");
  //User gives the value of interest rate by initializing double interestRate.
  double interestRate=choi.nextDouble();
  //Ask user to give how long will the money be invested.
  System.out.println("How long do you expect to invest (in years with integer value)?");
  //User gives the value of years by initializing int time.
  int time=choi.nextInt();
  //give the value of deposit to finalValue (after initializing double finalValue).
  double finalValue=deposit;
  //for (time) times, repeat below for loop.
  for(int n=0; n<time;n++)
  {
   //each time of repetition, finalValue increases by an amount for 1 year's increase.
   finalValue=finalValue*(1+interestRate/100);
  }
  //make empty lines to make output neater.
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
  //Print out what the answer (finalValue is answer since it is the value that comes after (time) years.
  System.out.println("Initial Investment $"+deposit+" invested at a rate of "+interestRate+"% for "+time+" years:");
  //round off the value of finalValue to the second decimal point because finalValue is a dollar value.
  //Then, print out the "finalValue"
  System.out.printf("End of year "+time+" is worth $%.2f \n",finalValue);
 }
}