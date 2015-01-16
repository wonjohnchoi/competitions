package project1;

/*
 * Wonjohn Choi
 * Rockwood,G
 * ICS3U1-02
 * Friday, September 25, 2009
 */
import java.util.*;

public class Project1SimpleCalculator{
  public static void main(String[] args){
    //Creat new Scanner, which is named choi.
    Scanner choi = new Scanner(System.in);
    //Print "Welcome to my calc".
    System.out.println("Welcome to Wonjohn's Calculator");
    //make a space to make this look better.
    System.out.println();
    //Ask user to put two numbers they want to multiply.
    System.out.println("Choose two numbers that you want to multiply");
    System.out.print("FirstValue:");
    //save the first value as j.
    double j=choi.nextDouble();
    System.out.print("SecondValue:");
    //save the second value as k.
    double k=choi.nextDouble();
    //make a number of spaces to make the outcome look better 
    //(i.e. it prevents user from looking the strange shape of input box).
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
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    //by repeating what user put, it shows the result without the strange shape of input box.
    System.out.println("FirstValue:" + j);
    System.out.println("SecondValue:" + k);
    //print "j * k = j *k", which is the answer. 
    //Also, .2 represents that this program will round off the answer to 2 decimal point.
    System.out.printf(j+ " * " + k + " = %.2f \n" , (j*k));
    System.out.println("Thanks for using Wonjohn's Calculator.");
    }
}

