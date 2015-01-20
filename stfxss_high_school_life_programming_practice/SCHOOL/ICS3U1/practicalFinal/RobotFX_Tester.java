package practicalFinal;

import becker.robots.*;
import java.io.*;
import java.util.*;

/**
 * subclass for Final Exam question
 * 
 * January 22th, 2009
 * @author Wonjohn Choi
 * @course ICS3U
 */
public class RobotFX_Tester 
{
    /**
     * main program test driver
     */
    public static void main (String[] args) throws IOException  
    {
      // Create the Environment
      City school = new City();
      RobotFX karel = new RobotFX (school, 0, 0, Direction.EAST, 10);

      // setup to get input from the user
      Scanner input = new Scanner(System.in);
      
      //store the first input to a String called commandString.
      String commandString = input.nextLine();
      
      //create a boolean variable done and will give two properties:
      //1. if 'Q' is input, give true value so that system knows we want to finish
      //2. if done is false, system knows we don't want to finish.
      boolean done=false;
   
      //since done is false, this while loop starts.
      while(!done)
      {
        //Since the input might be longer than one word, do same thing for all letters.
        //!done is condition so that if 'Q' is discovered, this for loop stops.  
        for(int i=0;i<commandString.length() && !done;i++)
        {
          //if the 'i'th letter is 'Q', give true value to done so that while loop will finish.
          if(commandString.charAt(i)=='Q')
          {
            done=true;
          }
          
          //use the method useInput so that if the robot will be active.
          //commandString gives input
          //karel give Robot
          //i gives which order of letter it will use.
          RobotFX.useInput(commandString, karel, i);
        }
        
        //get input using the method getInput(boolean done, Scanner input, String commandString).
        //boolean value done will make it not get new input if done is true.
        commandString=RobotFX.getInput(done, input, commandString);
      }

      //output that it is finished.
      System.out.println ("All Done");
    }
}
