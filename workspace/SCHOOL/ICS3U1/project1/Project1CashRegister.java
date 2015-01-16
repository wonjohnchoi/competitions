package project1;

/*
 * Wonjohn Choi
 * Rockwood,G
 * ICS3U1-02
 * Friday, September 25, 2009
 */
import java.util.*;

public class Project1CashRegister{
  public static void main(String[] args){   
    //creat new Scanner, which is named choi.
    Scanner choi=new Scanner(System.in);
    //Welcome ^^
    System.out.println("Welcome to Wonjohn's Cash Register");
    System.out.println("You purchased:");
    //put dollar sign so that when user put dollar value, user just needs to put number without $ sign.
    System.out.print("       $   ");
    //save the dollar value as a.
    double a=choi.nextDouble();
    System.out.print("       $   ");
    //save the dollar value as b.
    double b=choi.nextDouble();
    System.out.print("       $   ");
    //save the dollar value as c.
    double c=choi.nextDouble();
    System.out.print("       $   ");
    //save the dollar value as d.
    double d=choi.nextDouble();   
    System.out.print("       $   ");
    //save the dollar value as e.
    double e=choi.nextDouble();
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
    System.out.println();
    //Finally, without input box, it is possible to arrange the result. "\n" gave effect to line up the values.
    //Also, spaces in front of $ gives an effect that makes results neater. %10.2 means that 10 spaces + round off 
    //to the second decimal point
    System.out.println("You purchased:");
    System.out.printf("      $%10.2f \n",a);
    System.out.printf("      $%10.2f \n",b);
    System.out.printf("      $%10.2f \n",c);
    System.out.printf("      $%10.2f \n",d);
    System.out.printf("      $%10.2f \n",e);
    System.out.println("       ----------");
    System.out.printf("Total $%10.2f" , (a+b+c+d+e));    
    System.out.println();
  }
}