package teacher;

public class OutputExample1
{
  public static void main(String args[])
  {    
    // Single line (new line after)
    System.out.println("ICS3U1");
    
    // Both written on the same line
    System.out.print("Hello");
    System.out.print("There");
    
    // Go to new line and blank line
    System.out.println();
    System.out.println();

    int myInt = 67;
    float myFloat = 4.3f;   // double is the default
    double myDouble = 3.2763820076;
    char myChar = 'a';  // Single quotes
    
    // Output using concatenation
    System.out.println(" Int: " + myInt + " Float: " + myFloat + " Double: " + myDouble);

   // Watch out for BEDMAS
    System.out.println("Ans1: " + myInt + myFloat);
    System.out.println("Ans2: " + (myInt+myFloat) );
    System.out.println(myInt + myFloat + " :Ans3");
    
    // Force it to change types
    System.out.println("Letter: " + myChar + " Values: " + (int)myChar);
    System.out.println("Int: " + myInt + " letter: " + (char)myInt);
    
    // Formatting output
    System.out.printf("Value as int %d as hex %x and octal %o \n", myInt, myInt, myInt);
    // %n is new line character
    
    System.out.printf("Rounded number %.3f \n", myDouble);
    System.out.printf("Space it out %10.2f \n", myDouble);
  }
  
}
