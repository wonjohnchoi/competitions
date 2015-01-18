package teacher;

//Additional libraries to be used
import java.util.*;

public class InputExample1
{
  /**
   * Main method - starting point of a program
   */
  public static void main(String args[])
  { 
    Scanner sc = new Scanner(System.in);
    
    // Single line (new line after)
    System.out.print("Enter a value: ");
    int val = sc.nextInt();
    
    System.out.println("You entered: "+val);
    
  }
  
}