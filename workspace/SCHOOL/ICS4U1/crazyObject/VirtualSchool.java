package crazyObject;

/**
 * The "VistualSchool" class for the CrazyObjects problem.
 * @author Mr. Reid
 * @date Apr 2009
 * @version 1
 */
public class VirtualSchool
{
  /**
   *  Demo test driver for the Crazy Objects problem
   */
  public static void main (String[] args)
  {   // Create a student and display
    Student me = new Student("Reid");
    System.out.println("Student: "+me+"\n"); 
    
    // Attempt to enter the classroom of Mr. Strict
    ClassRoom ics4m1 = new ClassRoom("ICS4M1", "Mr. Reid");
    if (ics4m1.enterClass(me) == true)
    {
      System.out.println(me+" was allowed into class.");
    }
    else
    {
      System.out.println(me+" was not allowed into class.");        
    }
    
    System.out.println();
    
    // Attempt to enter the classroom of Mr. Strict
    ClassRoom idc4u1 = new ClassRoom("IDC4U1", "Mr. Strict");
    if (idc4u1.enterClass(me) == true)
    {
      System.out.println(me+" was allowed into class.");
    }
    else
    {
      System.out.println(me+" was not allowed into class.");        
    }
    
  } // main method
} // VirtualSchool class

